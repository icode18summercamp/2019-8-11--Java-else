// import url from './teacherConfig'

import {entity} from "./entityConfig";

let columnsName;
window.onload = function () {
    // 监控academy的值，加载其对应的专业
    // $("#academy").change(initDept);

    initColumns();
};

function initColumns() {
    $.ajax({
            url: "/entity",
            method: "get",
            data: {
                entity: entity
            },
            success: function (res) {
                if (res === "") {
                    return;
                }
                // let columnsName;
                if (typeof res == "string") {
                    columnsName = Object.keys((JSON.parse(res).rows)[0])
                } else {
                    columnsName = Object.keys(res[0])
                }

                initTableColumns(columnsName);
                initForm(columnsName);
            },
            complete: function (XMLHttpRequest, textStatus) {
                console.log("ajax complete");
                if (XMLHttpRequest.getResponseHeader("REDIRECT") !== null) {
                    window.location.href = XMLHttpRequest.getResponseHeader("urlLocation");
                }
            }

        }
    )
}

function initForm() {
    let $myForm = $("#myForm");

    let html = ""

    columnsName.forEach(function (name) {
        if (name === 'id') {
            html +=
                '<input type="hidden" id="id" name="id"/>'
            return;
        }
        html +=
            '  <div class="form-group row">\n' +
            '    <label for="' + name + '" class="col-sm-3 col-form-label">' + name + '</label>\n' +
            '    <div class="col-sm-9">\n' +
            '      <input type="text" class="form-control" id="' + name + '" name="' + name + '" placeholder="' + name + '">\n' +
            '    </div>\n' +
            '  </div>'
    })


    $myForm[0].innerHTML = html;
}

function initTableColumns() {
    let columns = [];
    columns.push({
        field: 'state',
        checkbox: true,
    });
    columns.push({
        title: "index",
        field: "index",
        align: 'center',
    });

    columnsName.forEach(function (name) {
        columns.push({
            title: name,
            field: name,
            align: "center",
            sortable: true
        })
    });

    console.log(columns);
    initTable(columns);
}

// 根据哪个按钮触发，决定表单是提交更新还是新增
$("#myModal").on("show.bs.modal", function (e) {
    console.log("show.bs.modal");

    // 动态模态框的header---->新增 | 更新
    let $button = $(e.relatedTarget);
    let $recipient = $button.data("whatever");

    let $modal = $(this);
    $modal.find(".modal-title").text($recipient);

    // 初始化模态框的form表单
    // initModal();
    //移除上次的校验配置，重新添加校验
    let $modalForm = $("#updateAndAddForm");
    // $modalForm.data("bootstrapValidator").destroy();
    // $modalForm.data("bootstrapValidator", null);
    // formValidator(e.relatedTarget.id);

    if (e.relatedTarget.id === "update") {
        // 将勾选栏的值写到模态框里
        let $getSelectRow = $("#table").bootstrapTable("getSelections", function (row) {
            return row;
        })[0];
        // $("#name").val($getSelectRow.name);
        // $("#gender").val($getSelectRow.gender);
        // $("#age").val($getSelectRow.age);
        // $("#academy").val($getSelectRow.academy);
        // $("#dept").val($getSelectRow.dept);
        // $("#salary").val($getSelectRow.salary);

        columnsName.forEach(function (name) {
            $("#" + name).val($getSelectRow[name])
        })

        // initDept();
        // if ($getSelectRow.gender === "男") {
        //     $("#male").prop("checked", "true")
        // } else {
        //     $("#female").prop("checked", "true")
        // }
    }
    // 增加
    else {
        columnsName.forEach(function (name) {
            $("#" + name).val("")
        })

        // initAcademy()
    }
});

// 增加、删除、更新操作后更新table
function refreshTable(params) {
    $("#table").bootstrapTable("refresh", {query: params});
}


// 模态框输入值初始化
function initModal() {
    /*
        console.log("`````init modal`````");

        $("#name").val("");
        $("input[name='gender']").prop("checked", false);
        $("#salary").val("");
        $("#age").val("");

        initAcademy();

        formValidator();
    */
}

// 学院初始化
function initAcademy() {
    // console.log("`````init academy`````");
    //
    // let text = ["<option selected disabled>请选择学院</option>"];
    // for (let academy in optionsMap) {
    //     text.push("<option>" + academy + "</option>");
    // }
    //
    // $("#academy")[0].innerHTML = text.join('');
    //
    // initDept()
}

// 根据选中的学院加载对应的专业
function initDept() {
    /*
        console.log("`````init dept`````");

        // 根据学院的值获取其对应的专业
        // note: 小心不要设为 const -。-
        let selected = $("#academy").val();
        console.log("selected academy" + selected);
        const deptSelect = $("#dept");
        let text = [];
        console.log(optionsMap[selected]);
        for (let dept in optionsMap[selected]) {
            text.push("<option>" + optionsMap[selected][dept] + "</option>");
        }

        deptSelect[0].innerHTML = text.join("");
        console.log(deptSelect);
    */
}

// 获取已选中的id，用于删除及更新操作
function getIdSelections() {
    return $.map($("#table").bootstrapTable("getSelections"), function (row) {
        return row.id
    })
}

// 获取已选中的tno，用于更新
// todo: tno作为主键应不允许用户修改，此处应有更好的解决办法
// function getTnoSelections() {
//     return $.map($("#table").bootstrapTable("getSelections"), function (row) {
//         return row.tno;
//     })
// }

// 删除事件
$("#remove").click(function () {
    console.log("remove click");

    const ids = getIdSelections();
    console.log(ids);
    if (confirm("确定删除勾选数据？")) {
        $.ajax({
            url: "/entity",
            method: "delete",
            async: false,
            data: {
                ids: ids,
                entity: entity
            },
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            traditional: true,
            success: function (result) {
                console.log("Ok");
                if (result === "true") {
                    toastr.success("删除成功！");
                } else {
                    toastr.error("删除失败！");
                }

                refreshTable({offset: 0})
            },
            error: function () {
                console.log("Ok");
                toastr.success("删除成功！");
            }
        });

        $("#remove").prop("disabled", true)
    }
});

// 新增&更新
$("#submit").click(function () {
    // Get the form instance
    const $form = $("#myForm");

    let data;
    let operation = $("#myModal").find(".modal-title").text();

    let method = operation === "新增" ? "post" : "put";

    data = $form.serialize();
    // if (operation === "新增") {
    //     data = $form.serialize();
    //     console.log(data);
    // } else if (operation === "更新") {
    //     data = $form.serialize();
    //     // data = data.toString() + "&id=" + getIdSelections()[0] + "&tno=" + getTnoSelections()[0];
    //     // 原生 put 传输数据会把中文编程字符码再传数
    //     // method = "put"
    // }

    // ajax本身就是举报刷新，不重新加载页面
    // 其的出现就是为了防止发送请求后刷新整个页面
    $.ajax({
        url: "/entity",
        method: method,
        async: false,
        data: data += "&entity=" + entity,
        success: function (result) {
            if (result === "true") {
                console.log("ok");
                toastr.success(operation + "成功！");
                // if (operation === "add") {
                //     initModal();
                // }

                refreshTable();
            } else {
                toastr.error(operation + "失败！");
            }
        },
        error: function (result) {
            toastr.error(result);
            toastr.error("出bug啦，赶紧联系我");
        }
    })
})


// modal框表单验证及提交操作
// function formValidator(operation) {
//     $("#updateAndAddForm").bootstrapValidator({
//         // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
//         feedbackIcons: {
//             valid: "glyphicon glyphicon-ok",
//             invalid: "glyphicon glyphicon-remove",
//             validating: "glyphicon glyphicon-refresh"
//         },
//         fields: {
//             name: {
//                 validators: {
//                     notEmpty: {
//                         message: "姓名不能为空"
//                     },
//                     stringLength: {
//                         min: 2,
//                         max: 20,
//                         message: "姓名最少2个字符，最多20个字符"
//                     },
//                     regexp: {
//                         regexp: /^[\u4e00-\u9fa5_a-zA-Z]+$/,
//                         message: "姓名只能包含中文、英文"
//                     }
//                 }
//             },
//             gender: {
//                 validators: {
//                     notEmpty: {
//                         message: "请选择性别"
//                     }
//                 }
//             },
//             age: {
//                 validators: {
//                     notEmpty: {
//                         message: "年龄不能为空"
//                     },
//                     regexp: {
//                         regexp: /^(?:[1-9][0-9]?|1[01][0-9]|120)$/,
//                         message: "年龄在1-120岁之间"
//                     }
//                 }
//             },
//             academy: {
//                 validators: {
//                     notEmpty: {
//                         message: "请选择学院"
//                     }
//                 }
//             },
//             dept: {
//                 validators: {
//                     notEmpty: {
//                         message: "请选择专业"
//                     }
//                 }
//             },
//             salary: {
//                 validators: {
//                     notEmpty: {
//                         message: "请输入薪水"
//                     },
//                     regexp: {
//                         regexp: /^[0-9]+([.]{1}[0-9]+)?$/,
//                         message: "薪水只能为正整数或者小数"
//                     }
//                 }
//             }
//         }
//     }).on("success.form.bv", function (e) {
//         // Prevent form submission
//         e.preventDefault();
//
//         // Get the form instance
//         const $form = $(e.target);
//
//         // console.log(bv);
//         console.log($form.serialize());
//
//         let data;
//         let operator;
//         if (operation === "add") {
//             data = $form.serialize();
//             console.log(data);
//             operator = "新增教师";
//         } else if (operation === "update") {
//             data = $form.serialize();
//             data = data.toString() + "&id=" + getIdSelections()[0] + "&tno=" + getTnoSelections()[0];
//             operator = "更新教师"
//             // 原生 put 传输数据会把中文编程字符码再传数
//             // method = "put"
//         }
//
//         // ajax本身就是举报刷新，不重新加载页面
//         // 其的出现就是为了防止发送请求后刷新整个页面
//         $.ajax({
//             url: url,
//             method: "post",
//             async: false,
//             data: data,
//             success: function (result) {
//                 if (result === "true") {
//                     console.log("ok");
//                     toastr.success(operator + "成功！");
//                     if (operation === "add") {
//                         initModal();
//                     }
//
//                     refreshTable();
//                 } else {
//                     toastr.error(operator + "失败！");
//                     if (operator === "新增教师") {
//                         console.log("tno已存在！");
//                         toastr.error("tno已存在！");
//                     }
//                 }
//             },
//             error: function (result) {
//                 toastr.error(result);
//                 toastr.error("出bug啦，赶紧联系我");
//             }
//         })
//     })
// }
package com.cqut.icode.servlet;


import com.cqut.icode.annotation.AutoWired;
import com.cqut.icode.entities.Girl;
import com.cqut.icode.entities.Page;
import com.cqut.icode.services.EntityService;
import com.cqut.icode.services.TeacherService;
import com.cqut.icode.services.impl.TeacherServiceImpl;
import com.cqut.icode.entities.Teacher;
import com.google.gson.Gson;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 谭强
 * @date 2019/5/9
 */

@WebServlet(value = "/teacher")
public class TeacherServlet extends HttpServlet {
    @AutoWired
    private static TeacherService teacherService;

    @AutoWired
    private static EntityService<Teacher> service;
    /**
     * 请求所有表单信息
     * @param req  ·
     * @param resp ·
     * @throws IOException ·
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("\n获取数据");

        Map<String, Object> result = new HashMap<>();

        if (req.getParameter("limit") == null) {
            Page page = new Page(1, 0);
            result.put("rows", service.listEntities(page, Teacher.class));
            result.put("total", service.getTotalNumber(Teacher.class));
        } else {
            Integer limit = Integer.parseInt(req.getParameter("limit"));
            Integer offset = Integer.parseInt(req.getParameter("offset"));

            String orderSort = req.getParameter("sortOrder");
            Page page = new Page(limit, offset,
                    req.getParameter("sortField"),
                    req.getParameter("sortOrder"));

            result.put("rows", service.listEntities(page, Teacher.class));
            result.put("total", service.getTotalNumber(Teacher.class));
        }


        System.out.println("=========== result.toString() ==============");
        System.out.println(result.toString());
        System.out.println("=========== JSONArray ==============");
        System.out.println(JSONArray.fromObject(result).toString());
        System.out.println("=========== Gson ==============");
        System.out.println(new Gson().toJson(result));

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().append(new Gson().toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Teacher teacher = getTeacher(req);
        boolean result;
//        if (req.getParameterMap().containsKey("id")) {
        if (req.getParameter("id") != null && !"".equals(req.getParameter("id"))) {
            System.out.println("\n更新数据");
            teacher.setId(Long.parseLong(req.getParameter("id")));
            teacher.setTno(Long.parseLong(req.getParameter("tno")));

            result = service.updateEntity(teacher);
        } else {
            System.out.println("\n插入数据");
            teacher.setTno(Long.parseLong((System.currentTimeMillis() + "").substring(2)));

            result = service.saveEntity(teacher);
        }
        resp.getWriter().write(result + "");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("\n删除数据");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));

        StringBuilder ids = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            ids.append(line);
        }
        String[] idss = ids.toString().split("&");

        List<Long> list = new ArrayList<>();
        for (String s : idss) {
            list.add(Long.parseLong(s.split("=")[1]));
        }
        System.out.println("ids[]: " + ids);
        System.out.println(list.toString());

        boolean result = service.removeEntities(list, Teacher.class);
        resp.getWriter().write(result + "");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private Teacher getTeacher(HttpServletRequest req) {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String academy = req.getParameter("academy");
        String dept = req.getParameter("dept");
        Float salary = Float.parseFloat(req.getParameter("salary"));
        return new Teacher(name, gender, age, academy, dept, salary);
    }
}

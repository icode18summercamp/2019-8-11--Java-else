package com.cqut.icode.servlet;

import com.cqut.icode.annotation.AutoWired;
import com.cqut.icode.entities.Girl;
import com.cqut.icode.entities.Page;
import com.cqut.icode.entities.Teacher;
import com.cqut.icode.services.EntityService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 谭强
 * @date 2019/6/9
 */
@WebServlet(value = "/girl")
public class GirlServlet extends HttpServlet {
    @AutoWired
    private static EntityService<Girl> service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("\n获取数据");

        Map<String, Object> result = new HashMap<>();

        if (req.getParameter("limit") == null) {
            Page page = new Page(1, 0);
            result.put("rows", service.listEntities(page, Girl.class));
        } else  {
            Integer limit = Integer.parseInt(req.getParameter("limit"));
            Integer offset = Integer.parseInt(req.getParameter("offset"));

            Page page = new Page(limit, offset,
                    req.getParameter("sortField"),
                    req.getParameter("sortOrder"));

            result.put("rows", service.listEntities(page, Girl.class));
        }

        result.put("total", service.getTotalNumber(Girl.class));
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("\n插入数据");
        Girl teacher = getGirl(req);
        boolean result;

        result = service.saveEntity(teacher);
        resp.getWriter().write(result + "");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("\n更新数据");
        Girl girl = getGirl(req);
        girl.setId(Long.parseLong(req.getParameter("id")));

        boolean result = service.saveEntity(girl);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        boolean result = service.removeEntities(list, Girl.class);
        resp.getWriter().write(result + "");

    }

    private Girl getGirl(HttpServletRequest req) {
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        return new Girl(name, age);
    }
}

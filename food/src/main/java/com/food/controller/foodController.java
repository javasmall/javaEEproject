package com.food.controller;

import com.food.bean.users;
import com.food.dao.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class foodController {
    @Autowired(required = true)
    private foodMapper foodmapper;

    @ResponseBody
    @RequestMapping(value = "/getallpath", method = RequestMethod.GET)
    public List<Map> getpath() {
        return foodmapper.getallpath();
    }

    @ResponseBody
    @RequestMapping(value = "/getalluser", method = RequestMethod.GET)
    public List<Map> getalluser() {
        return foodmapper.getalluser();
    }

    @ResponseBody
    @RequestMapping(value = "/getpathbyid", method = RequestMethod.GET)
    public List<Map> getpathbyid(int id) {
        return foodmapper.getpathbyid(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateuser(HttpServletRequest request, HttpServletResponse respose) {
        Cookie[] cookies = request.getCookies();//用于判断是否是管理员的操作
        boolean jud = false;
        if (cookies != null && cookies.length > 0)//判断是否已经登陆已经登陆
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid") && cookie.getValue() != null) {
                    List<Map> map = foodmapper.getadmin(cookie.getValue());
                    if (map.size() > 0)
                        jud = true;
                }
            }
        }
        if (!jud) {
            return "login";
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String detail = request.getParameter("detail");
        List<Map> alluser = foodmapper.getuserbyid(Integer.parseInt(username));
        if (alluser.size() > 0) {
            users user = new users();
            user.setSapid(Integer.parseInt(username));
            user.setPassword(password);
            user.setDetail(detail);
            foodmapper.updateuser(user);
        }
        return "admin";
    }

}

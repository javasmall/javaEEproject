package com.food.controller;

import com.food.bean.users;
import com.food.dao.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class usertroller {
    @Autowired(required = true)
    private foodMapper foodmapper;

    @RequestMapping(value = "/admin")
    public String admin(HttpServletRequest request, HttpServletResponse respose) {
        if (request.getMethod().equals("GET")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0)//判断是否已经登陆已经登陆
            {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userid") && cookie.getValue() != null) {
                        List<Map> map = foodmapper.getadmin(cookie.getValue());
                        if (map.size() > 0)
                            return "admin";
                        else {
                            return "login";
                        }
                    }
                }
            }
            return "login";
        } else {
            String username = request.getParameter("username");
            List<Map> map = foodmapper.getadmin(username);
            if (map.size() == 0) {
                return "login";
            }
            String password = (String) map.get(0).get("password");
            System.out.println(password);
            if (username.equals(map.get(0).get("username")) && password.equals(request.getParameter("password"))) {
                Cookie cookie = new Cookie("userid", username);
                respose.addCookie(cookie);
                return "admin";
            } else
                return "default";
        }
    }

    @RequestMapping(value = "/client")
    public String client(HttpServletRequest request, HttpServletResponse respose, ModelMap mode) {
        //System.out.println(request.getMethod());//POST
        if (request.getMethod().equals("GET")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0)//判断是否已经登陆已经登陆
            {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userid") && cookie.getValue() != null) {
                        return "upload";
                    }
                }
            }
            return "login";
        } else {
            String username = request.getParameter("username");
            List<Map> map = foodmapper.getuserbyid(Integer.parseInt(username));
            if (map.size() == 0) return "default";
            String password = (String) map.get(0).get("password");
            if (password.equals(request.getParameter("password"))) {
                Cookie cookie = new Cookie("userid", username);
                //mode.addAttribute("userid", username);
                respose.addCookie(cookie);
                return "upload";
            } else {
                return "default";
            }
        }
    }

    @RequestMapping(value = "/register")
    public String reginstr(HttpServletRequest request, HttpServletResponse respose) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String detail = request.getParameter("detail");
        List<Map> alluser = foodmapper.getuserbyid(Integer.parseInt(username));
        if (alluser.size() > 0) {
            return "default";
        } else {
            users user = new users();
            user.setSapid(Integer.parseInt(username));
            user.setPassword(password);
            user.setDetail(detail);
            foodmapper.insertUser(user);
            return "resucess";
        }
    }
}

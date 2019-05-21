package com.bigsai.pan.controller;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.entity.User;
import com.bigsai.pan.entity.admin;
import com.bigsai.pan.mapper.foodMapper;
import com.bigsai.pan.service.foodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired(required = false)
    private foodMapper foodMapper;
    @Autowired(required = false)
    private foodService foodService;

    @PostMapping("adminlogin")
    public String adminlogin(HttpServletRequest request)
    {
        HttpSession sessoin=request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        List<admin> list =foodMapper.getadmin( username);
        if(list.size()==0)
            return "redirect:/adminlogin.html";
        else
        {
            if(!list.get(0).getPassword().equals(password))
            {
                return "redirect:/adminlogin.html";
            }
            else
            {
                sessoin.setAttribute("username",username);
                sessoin.setAttribute("password",password);
                return "redirect:/index2";
            }
        }
    }
    @ResponseBody
    @GetMapping("getalluser")
    public List<User> getalluser(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        /*
        待验证安全性
         */
        return foodMapper.getalluser();

    }
    @ResponseBody
    @PostMapping("deleteuser")
    public String deleteuser(HttpServletRequest request)
    {
        try {
            String username=request.getParameter("username");
            foodMapper.deleteuser(username);
            return "delete 成功";
        }
        catch (Exception e)
        {
            return "delete 失败";
        }

    }
    @GetMapping("updateuser")
    public String updateuser(String username, Model model)
    {
        if(username.equals(null)||username.equals("")){}
        else
        {
            User user=foodMapper.getuserbyname(username).get(0);
            model.addAttribute("user",user);
        }
        return "updateuser";
    }
    @PostMapping("updateuser")
    public String updateuser(HttpServletRequest request,Model model)
    {
        try {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String details=request.getParameter("details");
        String phone=request.getParameter("phone");
        User user=new User(username,password,details,Long.parseLong(phone));
        model.addAttribute("user",user);
        if(username.equals(null)||password.equals(null)||details.equals(null)||phone.equals(null))
        {
            model.addAttribute("msg","不许为空");
            return "updateuser";
        }
        else
        {

            foodMapper.updateuser(user);
            model.addAttribute("msg","修改成功");
            return "updateuser";
        }}
        catch (Exception e)
        {
            model.addAttribute("msg","请注意格式");
            model.addAttribute("user",new User());
            return "updateuser";
        }

    }
    @ResponseBody
    @GetMapping("searchuser")
    public List<User> searchuser(String mohu,String details)
    {
        return foodMapper.searchuser(mohu,details);

    }
    @ResponseBody
    @GetMapping("admingetpath")
    public List<FilePath>admingetpath()
    {
        return foodMapper.getallpath();
    }

    @GetMapping ("changepwdadmin")
    public String changepasswordadmin(HttpServletRequest request,Model model)
    {
        model.addAttribute("name",request.getSession().getAttribute("username"));
        return "changepwdadmin";
    }
    @PostMapping("changepwdadmin")
    public ModelAndView changpasswordadmin(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("changepwdadmin");
        HttpSession session=request.getSession();
        String passold=request.getParameter("passold");System.out.println(passold);
        String passnew1=request.getParameter("passnew1");System.out.println(passnew1);
        String passnew2=request.getParameter("passnew2");System.out.println(passnew2);
        System.out.println(session.getAttribute("username")+" "+session.getAttribute("password"));
        if(passold.equals(null)||passnew1.equals(null)||passnew2.equals(null))
        {
            model.addObject("msg","None  is not allowed");

            System.out.println("null");
        }
        else if(!passnew1.equals(passnew2))
        {
            model.addObject("msg","two  password is not equall!");
            System.out.println("passnew");
        }
        else if (passold.equals(session.getAttribute("password")))
        {
            foodMapper.changpasswordadmin((String) session.getAttribute("username"),passnew1);
            model.addObject("msg","update succed ");
            session.setAttribute("password",passnew1);
        }
        else{model.addObject("msg","password is wrong");System.out.println("true");}
        model.addObject("name",session.getAttribute("username"));
        return model;
    }

    @GetMapping("adduser")
    public String adduser1(HttpServletRequest request,Model model)
    {
        model.addAttribute("msg","");
        return "adduser";
    }

    @PostMapping("adduser")
    public String adduser(HttpServletRequest request,Model model)
    {
        try {
            HttpSession session=request.getSession();
            String username=(String) session.getAttribute("username");
            if(username.equals(null)||username.equals(""))
            {
                model.addAttribute("msg","请正确登陆后操作");
            }
            String name=request.getParameter("username");
            String password=request.getParameter("password");
            String details=request.getParameter("details");
            String phones=request.getParameter("phone");
            List<User>userList=foodMapper.getuserbyname(name);
            if(userList.size()>0){model.addAttribute("msg","用户名已存在，请从新输入");return "adduser";}
            long phone=Long.parseLong(phones);
            User user=new User(name,password,details,phone);
            foodMapper.insertuser(user);
            model.addAttribute("msg","添加成功");
            return "adduser";
        }
        catch (Exception e)
        {
            model.addAttribute("msg","注意格式");
            return "adduser";
        }

    }

}

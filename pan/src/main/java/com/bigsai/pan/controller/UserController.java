package com.bigsai.pan.controller;

import com.bigsai.pan.entity.User;
import com.bigsai.pan.mapper.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.bigsai.pan.mapper.foodMapper;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired(required = false)
    private foodMapper foodMapper;
    @RequestMapping(value = "longin",method = RequestMethod.POST)
    public String longin(HttpServletRequest request)
    {
        HttpSession sessoin=request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        List<User>list =foodMapper.getuserbyname( username);
        if(list.size()==0)
            return "redirect:/login.html";
        else
        {
            if(!list.get(0).getPassword().equals(password))
            {
                return "redirect:/login.html";
            }
            else
            {
                sessoin.setAttribute("username",username);
                sessoin.setAttribute("password",password);
                return "redirect:/index1";
            }
        }
    }
    @PostMapping("changepassword")
    public ModelAndView changpassword(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("changepwd");
        HttpSession session=request.getSession();
        String passold=request.getParameter("passold");System.out.println(passold);
        String passnew1=request.getParameter("passnew1");System.out.println(passnew1);
        String passnew2=request.getParameter("passnew2");System.out.println(passnew2);
        System.out.println(session.getAttribute("username")+" "+session.getAttribute("password"));
        if(passold.equals(null)||passnew1.equals(null)||passnew2.equals(null))
        {
            model.addObject("msg","None is not allowed");

            System.out.println("null");
        }
        else if(!passnew1.equals(passnew2))
        {
            model.addObject("msg","two password is not equall!");
            System.out.println("passnew");
        }
        else if (passold.equals(session.getAttribute("password")))
        {
            foodMapper.changpassword((String) session.getAttribute("username"),passnew1);
            model.addObject("msg","update succed");
            session.setAttribute("password",passnew1);
            System.out.println("TRE");
        }
        else{model.addObject("msg","password is wrong");System.out.println("true");}
        model.addObject("name",session.getAttribute("username"));
        return model;
    }

}

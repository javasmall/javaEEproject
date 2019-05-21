package com.bigsai.pan.controller;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.entity.User;
import com.bigsai.pan.mapper.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class thymeleafController {
    @Autowired(required = false)
    private foodMapper foodMapper;
    @GetMapping("/index1")
    public String index1(HttpServletRequest request) {
        // TODO 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title", "我的第一个WEB页面");
        request.setAttribute("desc", "欢迎进入battcn-web 系统");

        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "index_student";
    }
    @GetMapping("changepwd")
    public String changepassword(Model model,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        model.addAttribute("name",session.getAttribute("username"));
        return "changepwd";
    }
    @GetMapping("index2")
    public String index2()
    {return "index_admin";}
    @GetMapping("userfile")
    public String userfile(String username,Model model)
    {
        User user=foodMapper.getuserbyname(username).get(0);
        model.addAttribute("user",user);
        List<FilePath>filePaths=foodMapper.getpathbyusername(username);
        model.addAttribute("path",filePaths);
       return "user_file";
    }


}

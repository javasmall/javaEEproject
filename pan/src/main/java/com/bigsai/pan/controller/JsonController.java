package com.bigsai.pan.controller;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.mapper.foodMapper;
import com.bigsai.pan.service.foodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class JsonController {
    @Autowired(required = false)
    private foodMapper foodMapper;
    @Autowired(required = false)
    private foodService foodService;
    @GetMapping("getpathadmin")
    public List<FilePath> getpathadmin(String type,String username,String filename,String details)
    {
        return foodMapper.getpathadmin(type,username,filename,details);
    }
    @GetMapping("getpath")
    public List<FilePath> getpath(HttpServletRequest request, String type)
    {
        HttpSession session=request.getSession();
        return foodService.getallpathdivide((String) session.getAttribute("username"),type);
    }
    @GetMapping("getallpath")
    public List getallpath(HttpServletRequest request)
    {return foodService.getallpath((String) request.getSession().getAttribute("username"));}
    @GetMapping("getallpathadmin")
    public  List getallpathadmin(HttpServletRequest request)
    {
        return foodMapper.getallpath();
    }
    @GetMapping("getallpathbytypeadmin")
    public  List getallpathadminby(String type, HttpServletRequest request)
    {
        return foodMapper.getallpath();
    }
}

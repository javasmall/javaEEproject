package com.food.controller;

import com.food.dao.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class downloadController {
    @Autowired(required = true)
    private foodMapper foodmapper;

    @RequestMapping(value = "/download")
    public String download(String filename, String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(filename);
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        System.out.println(filename);
        //设置文件MIME类型
        response.setContentType(session.getServletContext().getMimeType(filename));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO8859_1"));
        String fullFileName = session.getServletContext().getRealPath(path);  //获取绝对路径
        System.out.println(fullFileName);
        InputStream in = new FileInputStream(fullFileName);
        BufferedInputStream buf = new BufferedInputStream(in);
        OutputStream out = response.getOutputStream();
        BufferedOutputStream ou = new BufferedOutputStream(out);
        //PrintWriter out = response.getWriter();
        int b = 0;
        byte[] bite = new byte[1024];
        while ((b = buf.read(bite)) != -1) {
            ou.write(bite);
        }
        ou.close();
        buf.close();
        in.close();
        out.close();
        return null;
    }

    @RequestMapping(value = "/delete")
    public String delete(String filename, int id, String path, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String fullFileName = session.getServletContext().getRealPath(path);  //获取绝对路径
        System.out.println(fullFileName);
        File file = new File(fullFileName);
        if (file.isFile() || file.exists()) {
            file.delete();
        }
        foodmapper.deletepath(id, filename);
        return "upload";
    }

}

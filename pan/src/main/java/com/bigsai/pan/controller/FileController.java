package com.bigsai.pan.controller;


import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.mapper.foodMapper;
import com.bigsai.pan.service.foodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.bigsai.pan.mapper.foodMapper;

@Controller
public class FileController {
    @Autowired(required = false)
    private foodMapper foodMapper;
    @Autowired(required = false)
    private foodService foodService;
    @GetMapping("download")
    public String download(String filename,  HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
       String path="/"+(String) session.getAttribute("username")+"/"+filename;
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
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

    @ResponseBody
    @PostMapping("deletefileadmin")
    public  String deletefileadmin(String username,String filename,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String path="/"+username+"/"+filename;
        System.out.println(path);
        String fullFileName = session.getServletContext().getRealPath(path);  //获取绝对路径
        System.out.println(fullFileName);
        File file = new File(fullFileName);
        if (file.isFile() || file.exists()) {
            file.delete();
        }
        boolean jud=foodService.deletefile(username,filename);

        if(jud) return "删除成功";
        else return "删除失败";
    }
    @ResponseBody
    @PostMapping("deletefile")
    public  String deletefile(String filename,HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        try {
            String username= (String) session.getAttribute("username");
            if(username.equals(null)||username.equals(""))return "请先登陆";
        }
        catch (Exception e)
        {return  "错误"+e.toString();}
        String path="/"+(String) session.getAttribute("username")+"/"+filename;
        System.out.println(path);
        String fullFileName = session.getServletContext().getRealPath(path);  //获取绝对路径
        System.out.println(fullFileName);
        File file = new File(fullFileName);
        if (file.isFile() || file.exists()) {
            file.delete();
        }
        boolean jud=foodService.deletefile((String) session.getAttribute("username"),filename);

        if(jud) return "删除成功";
        else return "删除失败";
    }

    @PostMapping("fileupload")
    public String fileupload(HttpServletRequest request) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");//
        HttpSession session=request.getSession();
        String username=(String) session.getAttribute("username");
        String details=request.getParameter("details");
       // String password=(String) session.getAttribute("password");

        Part part = request.getPart("file");
        String type = request.getParameter("type");
        Calendar cal = Calendar.getInstance();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cal.getTime());
        String filename=getname(part);


        FilePath filePath=new FilePath(username,filename,"path",type,time,details);
        upload(part, session, filePath);

        return "redirect:htmlFiles/question/question_upload.html";
    }
    private void upload(Part part, HttpSession session, FilePath pa) throws IOException {
        String path = session.getServletContext().getRealPath("food/");
        //System.out.println(path);
        path += "" + pa.getUsername() + "/";
        String filename = pa.getName();
        File file = new File(path);
        String abluteadress = "/food/" + pa.getUsername() + "/" + filename;//
       System.out.println(path);
        pa.setPath(abluteadress);//存入数据库
       foodMapper.addfile(pa);
        if (!file.exists())//
        {
            file.mkdirs();
        }
        File file2 = new File(file, filename);//
        if (!file2.exists()) {
            file2.createNewFile();
        }
        InputStream in = part.getInputStream();
        OutputStream out = new FileOutputStream(file2);
        BufferedInputStream buf = new BufferedInputStream(in);
        BufferedOutputStream bufout = new BufferedOutputStream(out);
        byte by[] = new byte[1024 * 10];
        int q = 0;
        while ((q = buf.read(by)) != -1) {
            bufout.write(by);
            //by=new byte[1024]
        }
        System.out.println(path);
        bufout.close();
        buf.close();
        in.close();
        out.close();

    }
    public String getname(Part part)//获取文件名
    {
        String contentdisposition = part.getHeader("content-disposition");//form-data; name="file"; filename="jspѧϰ.txt"
        String[] filename = contentdisposition.split("=");//

        String filename1 = filename[filename.length - 1];//"jspѧϰ.txt"
        String filename2 = filename1.replace("\"", "");
        return filename2;
    }
}

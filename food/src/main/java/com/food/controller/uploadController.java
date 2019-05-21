package com.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.food.dao.foodMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.food.bean.path;

@Controller
public class uploadController {
    @Autowired(required = true)
    private foodMapper foodmapper;


    @RequestMapping(value = "/onfile")
    public String uploadfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");//
        response.setCharacterEncoding("UTF-8");
        Part part = request.getPart("file");
        String sapid = request.getParameter("sapid");//
        String type = request.getParameter("type");
        // String kownpoint=request.getParameter("kownpoint");//
        path path = new path();
        path.setType(type);
        path.setSapid(Integer.parseInt(sapid));
        upload(part, session, path);
        return "sucess";

    }

    public String getname(Part part)//获取文件名
    {
        String contentdisposition = part.getHeader("content-disposition");//form-data; name="file"; filename="jspѧϰ.txt"
        String[] filename = contentdisposition.split("=");//

        String filename1 = filename[filename.length - 1];//"jspѧϰ.txt"
        String filename2 = filename1.replace("\"", "");
        return filename2;
    }

    public void upload(Part part, HttpSession session, path pa) throws IOException {

        String path = session.getServletContext().getRealPath("food/");
        //System.out.println(path);
        path += "" + pa.getSapid() + "/";
        String filename = getname(part);
        File file = new File(path);
        String abluteadress = "/food/" + pa.getSapid() + "/" + filename;//
        Calendar cal = Calendar.getInstance();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cal.getTime());
        pa.setTime(time);
        pa.setName(filename);
        pa.setPath(abluteadress);//存入数据库
        foodmapper.insertPath(pa);
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
        bufout.close();
        buf.close();
        in.close();
        out.close();
    }

}

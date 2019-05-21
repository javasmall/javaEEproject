package com.example.judgle.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class judglecontroller {

    @ResponseBody
    @PostMapping("/judgle1")
    public String judgle(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String text = request.getParameter("text");
        System.out.println(username);
        System.out.println(password);
        System.out.println(text);
        if(text==null||text.equals(""))
            text="老师很负责任，进步了很多";
        try {
            return getrequest(username,password,text);
        }
        catch (Exception e)
        {
            return "评教失败,请检查账号密码或者是否已经完成评教";
        }


    }

    public String getrequest(String username, String password, String text) throws IOException {
        String url = "http://jwgl.just.edu.cn:8080/jsxsd/";
        Connection con = Jsoup.connect(url).timeout(30000);
        Response re = con.execute();
        Document doc = Jsoup.parse(re.body());
        Elements link = doc.select("form");

        Elements links = link.select("input");
        Map<String, String> map = new HashMap<>();
        for (Element e : links) {
            if (e.attr("name").equals("USERNAME")) {
                e.attr("value", username);
            }
            if (e.attr("name").equals("PASSWORD")) {
                e.attr("value", password);
            }
            if (e.attr("name").length() > 0) {
                map.put(e.attr("name"), e.attr("value"));
            }
        }
        //登陆部分
        Connection con2 = Jsoup.connect(url + "xk/LoginToXk").cookies(re.cookies()).timeout(2000);
        con2.data(map);
        con2.followRedirects(true);
        con2.method(Method.POST);
        Response re2 = con2.execute();
        if(re2.statusCode()!=200)return "账号密码输入错误";

//		/*
//		 * 登陆成功
//		 */
        String url3 = url + "xspj/xspj_find.do?Ves632DSdyV=NEW_XSD_JXPJ";
        Connection con3 = Jsoup.connect(url3).timeout(2000);
        con3.header("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");
        con3.header("Connection", "keep-alive");
        con3.cookies(re.cookies());
        Response res3 = con3.ignoreContentType(true).method(Method.GET).execute();
        Document doc3 = Jsoup.parse(res3.body());
        Elements elements = doc3.select("td a[href]");
        String shiyan = "http://jwgl.just.edu.cn:8080" + elements.get(0).attr("href");
        String lilun = "http://jwgl.just.edu.cn:8080" + elements.get(2).attr("href");
        judgle(shiyan, re.cookies(),text);//实验课理论课评教
        judgle(lilun, re.cookies(),text);
        // System.out.println(shiyan);
        return "评教成功";
    }
    private static void judgle(String url, Map<String, String> cookies,String textvalue) throws IOException {
        // TODO 自动生成的方法存根
        Document doc=Jsoup.connect(url).cookies(cookies).get();
        Elements elements=doc.select("td a[href]");
        int i=0;
        for(Element e:elements)
        {
            String judurl=e.attr("href");
            //javascript:JsMod('/js.................pe=view',1000,700)需要正则匹配//我直接用字符串切割
            String judur[]=judurl.split("'");
            judurl="http://jwgl.just.edu.cn:8080"+judur[1];
            try {
                judteacher(judurl,cookies,textvalue);
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
    }
    private static void judteacher(String judurl, Map<String, String> cookies,String textvalue) throws IOException {
        // TODO 自动生成的方法存根
        Document doc=Jsoup.connect(judurl).cookies(cookies).get();
        Elements link=doc.select("form");
        String actionurl="http://jwgl.just.edu.cn:8080"+link.attr("action");
        Elements links=link.select("input");
        Map<String, String>map=new TreeMap<>();
        for(Element e:links)//先处理所有隐藏的参数
        {
            if(e.attr("type").equals("hidden"))
            {
                map.put(e.attr("name"),e.attr("value"));
            }
        }
        Elements links2=link.select("#table1 tr");
        int index=0;//第一个不是参数
        for(Element e:links2)//处理button类元素
        {
            if(index==0) {index++;continue;}
            else if(index==links2.size()-1){
                String text=e.select("textarea").first().attr("name");
                map.put(text, textvalue);
            }
            else if(index==1) {
                Elements ele=e.select("input[type=radio]");
                Element NO2=ele.get(1);
                map.put(NO2.attr("name"), NO2.attr("value"));
                //System.out.println(NO2.attr("name"));
            }
            else {
                Elements ele=e.select("input[type=radio]");
                Element NO1=ele.get(0);
                map.put(NO1.attr("name"), NO1.attr("value"));
                //System.out.println(NO1.attr("name"));
            }
            index++;
            //System.out.println(e);
        }
        map.put("tj", "提  交");

        //最后提交form表单
        for(String a:map.keySet())
        {
            System.out.println(a+"   "+map.get(a));
        }
        Map<String, String>map2=new HashMap<>();
        Map<String, String>map3=new HashMap<>();
        Map<String, String>map4=new HashMap<>();
        Map<String, String>map5=new HashMap<>();
        map.put("pj06xh", "1");
        map2.put("pj06xh", "2");
        map3.put("pj06xh", "3");
        map4.put("pj06xh", "4");
        map5.put("pj06xh", "5");
        map.put("issubmit", "1");
        Connection con4=Jsoup.connect(actionurl).cookies(cookies).timeout(2000);
        con4.data(map).data(map2).data(map3).data(map4).data(map5);
        con4.referrer(judurl);
        Response res4=con4.method(Method.POST).execute();

    }
}

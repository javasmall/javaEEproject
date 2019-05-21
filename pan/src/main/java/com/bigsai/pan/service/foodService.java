package com.bigsai.pan.service;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.mapper.foodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class foodService {
    @Autowired(required = false)
    private foodMapper foodMapper;
    public List<FilePath> getallpathdivide(String username, String type)
    {
        List<FilePath>list=foodMapper.getpathbyusernametype(username, type);
        return list;

    }
    public List getallpath(String username)//
    {
        return  foodMapper.getpathbyusername(username);
    }
    public boolean deletefile(String username,String filename)
    {
        return foodMapper.deletefile(username,filename);
    }
}

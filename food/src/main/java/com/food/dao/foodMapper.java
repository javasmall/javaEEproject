package com.food.dao;


import com.food.bean.path;
import com.food.bean.users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface foodMapper {
    boolean insertPath(path path);

    boolean deletepath(@Param("sapid") int sapid, @Param("name") String name);

    boolean insertUser(users user);

    boolean updateuser(users user);

    List<Map> getallpath();

    List<Map> getpathbyid(int id);

    List<Map> getuserbyid(int id);

    List<Map> getalluser();

    List<Map> getadmin(String username);

}
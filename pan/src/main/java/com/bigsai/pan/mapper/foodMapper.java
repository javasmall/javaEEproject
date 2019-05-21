package com.bigsai.pan.mapper;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.entity.User;
import com.bigsai.pan.entity.admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface foodMapper {
    boolean addfile(FilePath filePath);//测试成功
    boolean deletefile(@Param("username")String username,@Param("filename") String filename);//测试成功
    boolean insertuser(User user);//测试成功
    boolean deleteuser(String username);
    boolean updateuser(User user);//测试成功
    boolean changpassword(@Param("username")String username,@Param("password")String password);
    boolean changpasswordadmin(@Param("username")String username,@Param("password")String password);

    List<User>searchuser(@Param("mohu") String mohu,@Param("details")String details);
    List<User> getuserbyname(String username);//测试成功
    List<User>getalluser();
    List<admin>getadmin(String username);
    List<FilePath> getallpath();//测试成功
    List<FilePath>getpathadmin(@Param("type") String type,@Param("username")String username,@Param("filename")String filename,@Param("details")String details);
    List<FilePath>getpathbyusernametype(@Param("username") String username,@Param("type") String type);//测试成功
    List<FilePath>getpathbyusername(@Param("username") String username);//测试成功


}

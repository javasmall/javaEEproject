package com.bigsai.pan;

import com.bigsai.pan.entity.FilePath;
import com.bigsai.pan.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bigsai.pan.mapper.foodMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PanApplicationTests {
    @Autowired(required = false)
    private foodMapper foodMapper;

    @Test
    public void test1()
    {
        FilePath file=new FilePath("66","66","66","66","66","25");
        foodMapper.addfile(file);
    }
    @Test
    public void test2()
    {
        foodMapper.deletefile("66","66");
    }
    @Test
    public void testinsertuser()
    {
        User user=new User("66","688","66",(long)666);
        foodMapper.updateuser(user);
    }
    @Test
    public void testselectbyuser()
    {
       foodMapper.changpassword("333","555");
    }

}


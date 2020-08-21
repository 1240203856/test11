package com.icbc.cdcp.pssp.service;

import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.exception.MyException;
import com.icbc.cdcp.pssp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    public UserService userService;

    @Test
    public void reg ()
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("113");
        userInfo.setPassword("123");
        Date now = new Date();
        userInfo.setUpdateTime(now);
        userInfo.setCreateTime(now);
        userInfo.setStatus(0);
        userInfo.setSalt("111");
        userInfo.setCompanyId(1);
        userInfo.setDepId(1);
        userInfo.setGroupId(1);
        userInfo.setGender(0);
        userInfo.setRoleId(1);
        userInfo.setPhoneNum("123");
        userInfo.setEmail("yaozeyi");
        userInfo.setLineLeader(1);
        try {
            userService.reg(userInfo);
            System.out.println("注册成功！");
        }catch (MyException e)
        {
            System.out.println(e.getErrorMsg());
        }
    }

    @Test
    public void login()
    {
        String username = "yaozeyi";
        String password = "123";
        try {
            userService.login(username,password);
        }catch (MyException e)
        {
            System.out.println(e.getErrorMsg());
        }
    }
}

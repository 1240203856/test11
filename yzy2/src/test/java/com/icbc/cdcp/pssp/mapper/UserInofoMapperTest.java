package com.icbc.cdcp.pssp.mapper;

import com.icbc.cdcp.pssp.dto.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserInofoMapperTest {

    @Autowired
    public UserInfoMapper userInfoMapper;

    @Test
    public void insert()
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("111");
        userInfo.setPassword("123");
        Date now = new Date();
        userInfo.setUpdateTime(now);
        userInfo.setCreateTime(now);
        userInfo.setStatus(0);
        userInfo.setSalt("111");
        userInfo.setCompanyId(1);
        userInfo.setDepId(1);
        userInfo.setGroupId(0);
        userInfo.setGender(0);
        userInfo.setRoleId(1);
        userInfo.setPhoneNum("123");
        userInfo.setEmail("123");
        userInfo.setLineLeader(1);
        Integer rows = userInfoMapper.insert(userInfo);
        System.out.println(rows);
        System.out.println(userInfo);
    }

    @Test
    public void findByUsername()
    {
        String userName = "1";
        UserInfo userInfo = userInfoMapper.selectByUsername(userName);
        System.out.println(userInfo);
    }

    @Test
    public void selectNameById()
    {
        String name = userInfoMapper.selectComNameById(1);
        System.out.println(name);
    }
}

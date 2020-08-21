package com.icbc.cdcp.pssp;

import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PsspApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    public UserInfoMapper userInfoMapper;

    @Test
    public void insert()
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("111");
        userInfo.setPassword("123");
        Integer rows = userInfoMapper.insert(userInfo);
        System.out.println(rows);
        System.out.println(userInfo);
    }

}

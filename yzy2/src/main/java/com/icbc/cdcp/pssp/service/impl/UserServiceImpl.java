package com.icbc.cdcp.pssp.service.impl;


import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.exception.MyException;
import com.icbc.cdcp.pssp.mapper.UserInfoMapper;
import com.icbc.cdcp.pssp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserById(int userId){
        return this.userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo findUserByUsername(String userName) {
        return this.userInfoMapper.selectByUsername(userName);
    }

    @Override
    public void reg(UserInfo userInfo) throws MyException {
        // 通过参数userInfo尝试获取用户名
        String userName = userInfo.getEmail();
        // 通过userInfoMapper的selectByUsername()方法执行查询
        UserInfo result = userInfoMapper.selectByUsername(userName);
        if (result!=null)
        {
            throw new MyException("用户名已存在，请重新注册！","0001"); //用户名已存在，请重新注册！
        }

        // 补全userInfo数据
        // 创建当前时间
        Date now = new Date();
        // 补全创建时间和修改时间
        userInfo.setCreateTime(now);
        userInfo.setUpdateTime(now);
        // 向参数userInfo中补全数据：salt,password
        String salt = UUID.randomUUID().toString();
        userInfo.setSalt(salt);
        String password = getMD5Password(userInfo.getPassword(),salt);
        userInfo.setPassword(password);
        // 向参数userInfo中补全数据，status(0)：表示在职
        userInfo.setStatus(0);
        // 通过userInfoMapper的insert()方法执行插入，并返回受影响的行数
        Integer rows = userInfoMapper.insert(userInfo);
        if (rows!=1)
        {
            throw new MyException("注册失败，请重新注册！","0002");
        }
    }

    /**
     * 执行密码加密，获取加密后的结果
     * @param password：原始密码
     * @param salt：盐值
     * @return：加密后的结果
     */
    public String getMD5Password(String password, String salt)
    {
        String result = password+salt+password;
        for (int i=0; i<3; i++)
        {
            result = DigestUtils.md5DigestAsHex(result.getBytes());
        }
        return result;
    }

    @Override
    public UserInfo login(String userName, String password) throws MyException {

        // 基于参数userName调用selectByUsername进行查询
        UserInfo userInfo = userInfoMapper.selectByUsername(userName);
        // 判断查询结果是否为Null
        if (userInfo==null)
        {
            throw new MyException("登录失败，用户名不存在！","0001");
        }
        // 判断查询结果中的status是否为0（1为离职）
        if (userInfo.getStatus()==1)
        {
            throw new MyException("登陆失败，用户数据已删除！","0002");
        }
        // 从查询结果中获取盐值
        String salt = userInfo.getSalt();
        // 基于salt和password，调用getMD5Password（）进行加密
        String md5Password = getMD5Password(password,salt);
        // 判断密码是否一致
        if (!md5Password.equals(userInfo.getPassword()))
        {
            throw new MyException("登陆失败，密码错误！","0003");
        }
        // 登录成功，返回userInfo信息
        UserInfo ret = new UserInfo();
        ret.setUserId(userInfo.getUserId());
        ret.setUserName(userInfo.getUserName());
        ret.setGender(userInfo.getGender());
        ret.setRoomNum(userInfo.getRoomNum());
        ret.setPhoneNum(userInfo.getPhoneNum());
        ret.setEmail(userInfo.getEmail());
        ret.setSalt(userInfo.getSalt());
        ret.setPassword(userInfo.getPassword());
        ret.setGroupId(userInfo.getGroupId());
        ret.setCompanyId(userInfo.getCompanyId());
        ret.setDepId(userInfo.getDepId());
        ret.setRoleId(userInfo.getRoleId());
        ret.setStatus(userInfo.getStatus());
        ret.setLineLeader(userInfo.getLineLeader());
        ret.setLeaderName(userInfoMapper.selectNameByUid(userInfo.getUserId()));
        ret.setCreateTime(userInfo.getCreateTime());
        ret.setUpdateTime(userInfo.getUpdateTime());
        ret.setGroupName(userInfoMapper.selectGroupNameById(userInfo.getGroupId()));
        ret.setDepName(userInfoMapper.selectDepNameById(userInfo.getDepId()));
        ret.setCompanyName(userInfoMapper.selectComNameById(userInfo.getCompanyId()));
        System.out.println(ret);
        return ret;
    }

    public void changePassword(String username, String oldPassword, String newPassword) throws MyException {
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        // 判断查询结果是否为Null
        if (userInfo==null)
        {
            throw new MyException("修改失败，用户名不存在！","0001");
        }
        // 从查询结果中获取盐值
        String salt = userInfo.getSalt();
        // 基于salt和oldPassword，调用getMD5Password（）进行加密
        String md5Password = getMD5Password(oldPassword,salt);
        // 判断密码是否一致
        if (!md5Password.equals(userInfo.getPassword()))
        {
            throw new MyException("原始密码错误，修改密码失败！","0001");
        }
        String password = getMD5Password(newPassword,salt);
        userInfoMapper.changePassword(username,password);
    }

    public void retrievePassword(String username, Integer quesNum, String ans, String password) throws MyException {
        UserInfo answer = userInfoMapper.selectByUsername(username);
        // 不能获取部分字段：Column 'USER_ID' not found.
        // 修改了UserInfo中的构造函数，重写，修改了resultMap，添加了一个AnswerMap
        //UserInfo answer1 = userInfoMapper.selectAnswer(username);
        if (quesNum==1)
        {
            if (!ans.equals(answer.getExt1()))
                throw new MyException("密保答案错误，修改密码失败！","0001");
        }
        else if (quesNum==2)
        {
            if (!ans.equals(answer.getExt2()))
                throw new MyException("密保答案错误，修改密码失败！","0002");
        }
        else
        {
            if (!ans.equals(answer.getExt3()))
                throw new MyException("密保答案错误，修改密码失败！","0003");
        }
        // 从查询结果中获取盐值
        String salt = answer.getSalt();
        String pass = getMD5Password(password,salt);
        userInfoMapper.changePassword(username,pass);
    }

    @Override
    public List<UserInfo> selectAll(Integer role_id, Integer pos_id) {
        Integer grp_id=null, dep_id=null, com_id=null;
        if (role_id==1) //小组长
            grp_id=pos_id;
        else if (dep_id==2) //部门领导
            dep_id=pos_id;
        else // 中心领导
            com_id=pos_id;
        List<UserInfo> ret = userInfoMapper.selectAll(role_id,dep_id,com_id);
        return ret;
    }

    public List<UserInfo> selectByInfo(String username, Integer gender, Integer roomNum, String phone,
                                       String email, Integer grpId, Integer depId, Integer comId, Integer roleId, Integer isDelete)
    {
        List<UserInfo> ret = userInfoMapper.selectByInfo(username, gender, roomNum, phone, email, grpId, depId, comId, roleId, isDelete);
        return ret;
    }

    public void updateUserInfo(String oldEmail,String username,Integer gender, String roomNum ,String phoneNum
            ,String email,Integer depId,Integer comId,Integer grpId ,Integer roleId, Integer status) throws MyException
    {
        int rows = userInfoMapper.updateUserInfo(oldEmail,username,gender,roomNum , phoneNum, email,depId,comId,grpId ,roleId, status);
        if (rows!=1)
        {
            throw new MyException("更新失败，未知原因","0002");
        }
    }

    public void updateLineLeader(String email, Integer lineLeader) throws MyException
    {
        int rows = userInfoMapper.updateLineLeader(email,lineLeader);
        if (rows!=1)
        {
            throw new MyException("更新失败，未知原因","0002");
        }
    }

    public void deleteUserInfo(String email) throws MyException {
        int rows = userInfoMapper.deleteUserInfo(email);
        if (rows!=1)
        {
            throw new MyException("删除用户信息失败，未知原因！","0002");
        }
    }

}

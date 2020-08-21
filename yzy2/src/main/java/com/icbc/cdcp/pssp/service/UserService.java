package com.icbc.cdcp.pssp.service;

import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.exception.MyException;

import java.util.List;
import java.util.Map;

public interface UserService {
    public UserInfo findUserById(int userId);

    /**
     * 根据用户名（邮箱）查询用户数据
     * @param userName
     * @return
     */
    public UserInfo findUserByUsername(String userName);

    /**
     * 注册
     * @param userInfo
     * @return
     */
    public  void reg(UserInfo userInfo) throws MyException;

    /**
     * 登录
     * @param userName：用户名（邮箱）
     * @param password：密码
     * @return
     */
    public UserInfo login(String userName, String password) throws MyException;

    /**
     * 登录后修改密码
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @throws MyException
     */
    public void changePassword(String userName, String oldPassword, String newPassword) throws MyException;

    /**
     * 忘记密码后找回密码
     * @param username
     * @param quesNum
     * @param ans
     * @param password
     */
    public void retrievePassword(String username, Integer quesNum, String ans, String password) throws MyException;

    /**
     * 查询所有符合条件的数据
     * @param role_id：用户角色
     * @param pos_id：小组/部门/中心id
     * @return
     */
    public List<UserInfo> selectAll(Integer role_id, Integer pos_id);

    /**
     * 根据条件查询用户信息
     * @param username
     * @param gender
     * @param roomNum
     * @param phone
     * @param email
     * @param grpId
     * @param depId
     * @param comId
     * @param roleId
     * @param isDelete
     * @return
     */
    public List<UserInfo> selectByInfo(String username, Integer gender, Integer roomNum, String phone,
                                       String email, Integer grpId, Integer depId, Integer comId, Integer roleId,Integer isDelete);

    /**
     * 编辑用户信息：小组长
     * @return
     */
    public void updateUserInfo(String oldEmail,String username,Integer gender, String roomNum ,String phoneNum
            ,String newEmail,Integer depId,Integer comId,Integer grpId ,Integer roleId, Integer status) throws MyException;

    /**
     * 编辑用户信息：直系领导
     * @param email
     * @param lineLeader
     */
    public void updateLineLeader(String email, Integer lineLeader) throws MyException;

    /**
     * 删除用户信息
     * @param email
     */
    public void deleteUserInfo(String email) throws MyException;
}

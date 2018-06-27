package com.izuche.springbootdemo.permission.service;

import java.util.List;

import com.izuche.springbootdemo.permission.vo.RegisterMsg;

public interface RegisterService {
	//插入注册信息
	public void insertRegisterMsg(RegisterMsg registerMsg) throws Exception;
	//根据手机号查询用户信息
	public String findMsgByPhoneNumber(String phoneNumber) throws Exception;
}

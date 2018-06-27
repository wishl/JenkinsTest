package com.izuche.springbootdemo.permission.dao;

import java.util.List;

import com.izuche.springbootdemo.permission.vo.RegisterMsg;

public interface LoginDao {
	public RegisterMsg findMsgByPhoneNumberAndPassword(String phoneNumber,String password) throws Exception;

}

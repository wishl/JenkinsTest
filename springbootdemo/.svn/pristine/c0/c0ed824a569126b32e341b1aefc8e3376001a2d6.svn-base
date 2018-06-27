package com.izuche.springbootdemo.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izuche.springbootdemo.permission.dao.LoginDao;
import com.izuche.springbootdemo.permission.service.LoginService;
import com.izuche.springbootdemo.permission.vo.RegisterMsg;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class) 
	public RegisterMsg findMsgByPhoneNumberAndPassword(String phoneNumber, String password) throws Exception{
		return loginDao.findMsgByPhoneNumberAndPassword(phoneNumber, password);
	}

}

package com.izuche.springbootdemo.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izuche.springbootdemo.permission.dao.RegisterDao;
import com.izuche.springbootdemo.permission.service.RegisterService;
import com.izuche.springbootdemo.permission.vo.RegisterMsg;
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private RegisterDao registerDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class) 
	public void insertRegisterMsg(RegisterMsg registerMsg) throws Exception {
		registerDao.insertRegisterMsg(registerMsg);
		
	}

	@Override
	public String findMsgByPhoneNumber(String phoneNumber) throws Exception {
		return registerDao.findMsgByPhoneNumber(phoneNumber);
	}

}

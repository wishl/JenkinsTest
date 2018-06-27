package com.izuche.springbootdemo.permission.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.izuche.springbootdemo.permission.dao.LoginDao;
import com.izuche.springbootdemo.permission.vo.RegisterMsg;
@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public RegisterMsg findMsgByPhoneNumberAndPassword(String phoneNumber, String password) throws Exception {
		String sql="select phone_number,password,email,calendar,file_path,sex,hobby,study_experience from t_user where phone_number=? and "
				+ "password=?";
		//RegisterMsg msg=jdbcTemplate.queryForObject(sql, phoneNumber,password, RegisterMsg.class);
		Map<String,Object> map=new HashMap<String,Object>();
		try{
			map=jdbcTemplate.queryForMap(sql, phoneNumber,password);
			
		}catch(EmptyResultDataAccessException e){
			throw new Exception("没有该用户信息！");
		}catch(Exception e){
			throw new Exception("用户信息查询异常！");
		}		
		RegisterMsg msg=new RegisterMsg();		
		msg.setCalendar((String)map.get("calendar"));
		msg.setEmail((String)map.get("email"));
		msg.setFilePath((String)map.get("file_path"));
		msg.setHobby((String)map.get("hobby"));
		msg.setPassword(password);
		msg.setPhoneNumber(phoneNumber);
		msg.setSex((String)map.get("sex"));
		msg.setStudyExperience((String)map.get("study_experience"));
		
		return msg;
	}

}

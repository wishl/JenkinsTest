package com.izuche.springbootdemo.permission.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.izuche.springbootdemo.permission.dao.RegisterDao;
import com.izuche.springbootdemo.permission.vo.RegisterMsg;
@Repository
public class RegisterDaoImpl implements RegisterDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void insertRegisterMsg(RegisterMsg registerMsg) throws Exception{
		String sql="insert into t_user (phone_number,password,email,calendar,file_path,sex,hobby,study_experience)"
				+ " values (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, registerMsg.getPhoneNumber(),registerMsg.getPassword(),registerMsg.getEmail(),
				registerMsg.getCalendar(),registerMsg.getFilePath(),registerMsg.getSex(),registerMsg.getHobby(),
				registerMsg.getStudyExperience());
		
	}
	@Override
	public String findMsgByPhoneNumber(String phoneNumber) throws Exception {
		String sql="select phone_number,password,email,calendar,file_path,sex,hobby,study_experience from t_user where phone_number=?";
		//RegisterMsg msg=jdbcTemplate.queryForObject(sql, phoneNumber,password, RegisterMsg.class);
		Map<String,Object> map=null;
		try{
			map=jdbcTemplate.queryForMap(sql, phoneNumber);
			
		}catch(EmptyResultDataAccessException e){
			return "0";
		}catch(Exception e){
			throw new Exception("用户信息查询异常！");
		}		
		if(null!=map&&map.size()>0){
			return "1";
		}else{
			return "0";
		}
	}	
}

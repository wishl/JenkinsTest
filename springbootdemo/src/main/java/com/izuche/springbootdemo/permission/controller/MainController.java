package com.izuche.springbootdemo.permission.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.izuche.springbootdemo.permission.redis.RedisUtil;
import com.izuche.springbootdemo.permission.service.LoginService;
import com.izuche.springbootdemo.permission.service.RegisterService;
import com.izuche.springbootdemo.permission.utils.ConstantUtils;
import com.izuche.springbootdemo.permission.utils.CookieUtils;
import com.izuche.springbootdemo.permission.utils.JsonUtil;
import com.izuche.springbootdemo.permission.vo.RegisterMsg;
import com.izuche.springbootdemo.permission.vo.ResponseMsg;

@RestController
@RequestMapping
//@CrossOrigin
public class MainController {
	private static final Logger logger=LoggerFactory.getLogger(MainController.class);
	@Autowired
	private RegisterService registerService;	
	@Autowired
	private LoginService loginService;
	@Autowired 
	private RedisUtil redisUtil;
	//用户注册
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseMsg register(HttpServletRequest request) throws ParseException{
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
////		创建一个通用的多部分解析器  
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
////	    判断 request 是否有文件上传,即多部分请求  
//	    if(multipartResolver.isMultipart(request)) { 
			MultipartHttpServletRequest params=(MultipartHttpServletRequest)request;
			String phone_number=params.getParameter("phoneNumber");
			String value=null;
			try {
				value = redisUtil.get(ConstantUtils.PHONE_PREFIX+phone_number);
			} catch (Exception e1) {
				logger.error("redis查询异常！");
				e1.printStackTrace();
			}
			if(null==value){
				try {
					String flag=registerService.findMsgByPhoneNumber(phone_number);
					if("1".equals(flag)){
						ResponseMsg responseMsg=new ResponseMsg();
	                    responseMsg.setStatusCode("0");
	                    responseMsg.setMessage("该用户已存在！");
	                    responseMsg.setRegisterMsg(null);
	                    return responseMsg;      
					}
				} catch (Exception e) {
					logger.error("查询用户信息异常！");
					e.printStackTrace();
				}
			}else{
				ResponseMsg responseMsg=new ResponseMsg();
                responseMsg.setStatusCode("0");
                responseMsg.setMessage("该用户已存在！");
                responseMsg.setRegisterMsg(null);
                return responseMsg; 
			}
			List<MultipartFile> files=((MultipartHttpServletRequest)request).getFiles("file");
	        String file_path="";
	        MultipartFile file = null;      
	        BufferedOutputStream stream = null;      
	        for (int i = 0; i < files.size(); ++i) {      
	            file = files.get(i);      
	            if (!file.isEmpty()) {      
	                try {    
	                	file_path=ConstantUtils.PATH+file.getOriginalFilename()+",";
	                    byte[] bytes = file.getBytes();      
	                    stream = new BufferedOutputStream(new FileOutputStream(      
	                            new File(ConstantUtils.PATH+file.getOriginalFilename())));      
	                    stream.write(bytes);      
	                    stream.close();      
	                } catch (Exception e) {      
	                    stream = null;  
	                    ResponseMsg responseMsg=new ResponseMsg();
	                    responseMsg.setStatusCode("0");
	                    responseMsg.setMessage("You failed to upload " + i + " => "      
	                            + e.getMessage());
	                    responseMsg.setRegisterMsg(null);
	                    return responseMsg;    
	                }      
	            } else {  
	            	ResponseMsg responseMsg=new ResponseMsg();
                    responseMsg.setStatusCode("0");
                    responseMsg.setMessage("You failed to upload " + i      
	                        + " because the file was empty.");
                    responseMsg.setRegisterMsg(null);
                    return responseMsg;      
	            }    
	        } 
	        RegisterMsg registerMsg=new RegisterMsg();
			registerMsg.setPhoneNumber(phone_number);
			registerMsg.setPassword(params.getParameter("password"));
			registerMsg.setEmail(params.getParameter("email"));
			registerMsg.setCalendar(params.getParameter("calendar"));
			registerMsg.setFilePath(file_path.substring(0, file_path.length()-1));
			registerMsg.setSex(params.getParameter("sex"));
			System.out.println(params.getParameter("sex"));
			registerMsg.setHobby(params.getParameter("hobby"));
			registerMsg.setStudyExperience(params.getParameter("studyExperience"));
			Cookie cookie=new Cookie("phoneNumber",params.getParameter("phoneNumber"));
			CookieUtils.saveCookie(cookie);
			try {
				registerService.insertRegisterMsg(registerMsg);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				ResponseMsg responseMsg=new ResponseMsg();
                responseMsg.setStatusCode("0");
                responseMsg.setMessage("exception");
                responseMsg.setRegisterMsg(null);
                return responseMsg;
			}
			ResponseMsg responseMsg=new ResponseMsg();
            responseMsg.setStatusCode("0");
            responseMsg.setMessage("注册成功！");
            responseMsg.setRegisterMsg(null);
            return responseMsg;
//	    }
//	    return "";
	}	
	//用户登录接口
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseMsg login(@RequestBody String json){
		System.out.println(json);
		Map<String,String> map=(Map<String,String>)JSONObject.parse(json);
		String phoneNumber=map.get("phoneNumber");
		String password=map.get("password");
		System.out.println(phoneNumber+":"+password);
		ResponseMsg responseMsg=new ResponseMsg();
		//String value=CookieUtils.getCookie(phoneNumber);
		RegisterMsg registerMsg=null;
		try {
			registerMsg = loginService.findMsgByPhoneNumberAndPassword(phoneNumber, password);
			
		} catch (Exception e) {
			responseMsg.setStatusCode("0");
			responseMsg.setMessage("用户信息查询异常！");
			responseMsg.setRegisterMsg(null);
			logger.error(e.getMessage());
			e.printStackTrace();
			return responseMsg;
		}
		responseMsg.setStatusCode("1");
		responseMsg.setMessage("用户信息查询成功！");
		responseMsg.setRegisterMsg(registerMsg);
		
		CookieUtils.saveCookie(new Cookie("phoneNumber",phoneNumber));
		return responseMsg;
	}
}

package com.yjb.business.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.yjb.business.app.form.RequestCntDto;
import com.yjb.business.app.form.RequestLoginDto;
import com.yjb.business.app.form.RequestRegistDto;
import com.yjb.business.app.service.LoginService;
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping("/getUserByPwd")
	public String getUserByPwd( RequestLoginDto login) {
		return loginService.getUserByPwd(login);
	}
	
//	@GetMapping("/getUserCnt")
//	public String getUserCnt( RequestCntDto cnt) {
//		return loginService.getUserCnt(cnt);
//	}
	
	@GetMapping("/getUserCnt")
	public String getUserCnt(String username) {
		return loginService.getUserCnt(username);
	}

	@PostMapping("/regist")
	public String insertUser(RequestRegistDto RequestRegistDto) {
		return loginService.insertUser(RequestRegistDto);
	}
	
	@GetMapping("/getLimitUserCount")
	public String getLimitUserCount( Integer limit) {
		return loginService.getLimitUserCount(limit);
	}
	
	@GetMapping("/insteUsers")//01
	public String insteUsers(int id) {
		return loginService.insteUsers(id);
	}
	
	@GetMapping("/insteRoles")//02
	public String insteRoles(int uid,int rid) {
		return loginService.insteRoles(uid, rid);
	}
	
	@GetMapping("/deleteRoles")//03
	public String deleteRoles(int uid,int rid) {
		return loginService.deleteRoles(uid, rid);
	}
	
	@GetMapping("/deleteUsers")//04
	public String deleteUsers(int id) {
		return loginService.deleteUsers(id);
	}
	
	@GetMapping("/upData")//05
	public String updata(int id, String email, String pwd) {
		return loginService.updata(id, email, pwd);
	}
	
}

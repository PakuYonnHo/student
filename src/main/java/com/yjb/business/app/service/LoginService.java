package com.yjb.business.app.service;

//import com.yjb.business.app.form.RequestCntDto;
import com.yjb.business.app.form.RequestLoginDto;
import com.yjb.business.app.form.RequestRegistDto;

public interface LoginService {
	public String getUserCnt(String username);
    public String getUserByPwd(RequestLoginDto loginRequest);
    public String insertUser(RequestRegistDto RequestRegistDto);
    public String getLimitUserCount(int limit);
    public String insteUsers(int id);//01
    public String insteRoles(int uid,int rid);//02
    public String deleteRoles(int uid,int rid);//03
    public String deleteUsers(int id);//04
    public String updata(int id, String email, String pwd);//05
}

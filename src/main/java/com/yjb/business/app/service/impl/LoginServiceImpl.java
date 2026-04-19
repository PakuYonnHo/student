package com.yjb.business.app.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yjb.business.app.dao.UserMapper;
import com.yjb.business.app.dao.UserRolesMapper;
import com.yjb.business.app.dto.RequestUserDto;
import com.yjb.business.app.dto.RequestUserRolesDto;
import com.yjb.business.app.entity.UserEntity;
//import com.yjb.business.app.form.RequestCntDto;
import com.yjb.business.app.form.RequestLoginDto;
import com.yjb.business.app.form.RequestRegistDto;
import com.yjb.business.app.service.LoginService;

@Service// 
public class LoginServiceImpl extends BaseService<UserEntity> implements LoginService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRolesMapper userRolesMapper;
	
	public String insteUsers(int id) {
		try {
	        int cnt = userMapper.insteUsers(id);
	        if (cnt > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success").toString();
	            // return gson.toJson(cnt).toString();
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//01
	
	public String insteRoles(int uid,int rid) {
		try {
	        int cnt = userMapper.insteRoles(uid,rid);
	        if (cnt > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success").toString();
	            // return gson.toJson(cnt).toString();
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//02
	
	public String deleteRoles(int uid,int rid) {
		try {
	        int cnt = userMapper.deleteRoles(uid, rid);

	        if (cnt > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success").toString();
	            //return gson.toJson(cnt).toString();
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//03
	
	public String deleteUsers(int id) {
		try {
	        int cnt = userMapper.deleteUsers(id);

	        if (cnt > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success").toString();
	            //return gson.toJson(cnt).toString();
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//04
	
	public String updata(int id, String email, String pwd) {
		try {
	        int cnt = userMapper.updata(id, email, pwd);
	        if (cnt > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success").toString();
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//05
	
	public String getUserByPwd(RequestLoginDto loginRequest) {
		Gson gson = new Gson();
		RequestUserDto dto = new RequestUserDto();
		dto.setUsername(loginRequest.getUsername());
		dto.setPassword(loginRequest.getPassword());
    	List<UserEntity> bookObj = userMapper.selectUserById(dto);
    	if(bookObj.size() > 0) {
    		System.out.println(bookObj.get(0));
    		return gson.toJson(bookObj.get(0)).toString();
    	}
		String book = gson.toJson(new UserEntity()).toString();
        return book;
	}

	@Override
	public String insertUser(RequestRegistDto requestRegistDto) {
		RequestUserDto dto = new RequestUserDto();
		BeanUtils.copyProperties(requestRegistDto, dto);
		int cnt = userMapper.insertUser(dto);
		if(cnt ==0) {
			return null;
		}
		List<UserEntity> userList = userMapper.selectUserByPwd(dto);
		
		RequestUserRolesDto requestUserRolesDto= new RequestUserRolesDto();
		requestUserRolesDto.setUserId(userList.get(0).getId());
		requestUserRolesDto.setRoleId(requestRegistDto.getType());
		cnt = userRolesMapper.insertUserRoles(requestUserRolesDto);
		if(cnt ==0) {
			return null;
		}
		
		userList = userMapper.selectUserById(dto);
    	if(userList.size() > 0) {
    		System.out.println(userList.get(0));
    		Gson gson = new Gson();
    		return gson.toJson(userList.get(0)).toString();
    	}
    	return null;
	}
	
	@Override
	public String getLimitUserCount( int limit) {
		Gson gson=new Gson();
		List<UserEntity>userList=userMapper.selectUserLimit(limit);
		if(userList.size()>0) {
			String userInfo=gson.toJson(userList).toString();
			return userInfo;
		}
		return "";
	}

	
	public String getUserCnt(String userName) {
		Gson gson = new Gson();
		Integer bookObj = userMapper.selectUserCnt(userName);
		if (bookObj > 0) {
			System.out.println(bookObj);
			return gson.toJson(String.valueOf(bookObj));
		}
		return "none";
	}
}

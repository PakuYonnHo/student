package com.yjb.business.app.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.yjb.business.app.dao.BooksMapper;
import com.yjb.business.app.dto.RequestUserDto;
import com.yjb.business.app.entity.BooksEntity;
import com.yjb.business.app.form.ResponseUserDto;
import com.yjb.business.app.service.BooksService;
import com.yjb.business.app.utils.HttpClientUtils;

@Service //直接实体化，不用new
public class BooksServiceImpl extends BaseService<BooksEntity> implements BooksService {

	private static final String Null = null;
	@Autowired //看不见的实体化
	BooksMapper booksMapper;

	public String getBooksList() {
		Gson gson = new Gson();//解析gson文件
		List<BooksEntity> list = booksMapper.selectBooksLimit(100);
		System.out.println(list);
		String books = gson.toJson(list).toString();
		return books;
	}
	
	public String getBookInfoByName(String title) {
		try {
	        if (ObjectUtils.isEmpty(title)) {
	            return "title is required";
	        }
	        List<BooksEntity> list = booksMapper.getBookInfoByName(title);
	        Gson gson = new GsonBuilder()
	                .serializeNulls()
	                .create();
	        return gson.toJson(list).toString();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}//20260215
	
	@Override
	public String insteBook(String title, String author) {
		// TODO 自動生成されたメソッド・スタブ
		try {
	        if (ObjectUtils.isEmpty(title) || ObjectUtils.isEmpty(author)) {
	            return "title/author is required";
	        }
	        int rows = booksMapper.insertBook(title, author);
	        if (rows > 0) {
	            Gson gson = new Gson();
	            return gson.toJson("success");
	            // return gson.toJson(rows);
	        }
	        return "none";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}

	public String getBookById(RequestUserDto dto) {
		try {
			String result1 = HttpClientUtils.getApiResult("http://localhost:8086", "/api/edi/userInfoByUserPwd?", null,
					null, "id=1&name="+ dto.getUsername()+"&pwd="+ dto.getPassword()+"");

			Gson gson = new Gson();
			ResponseUserDto userDTO = gson.fromJson(result1, ResponseUserDto.class);

			System.out.println("result: " + result1);

			if(!ObjectUtils.isEmpty(userDTO)) {
				String result2 = HttpClientUtils.getApiResult("http://localhost:8085", "/api/user/userInfoByUserPwd?", null,
						null, "uuid="+userDTO.getUuid()+"&name="+userDTO.getUsername()+"&pwd="+userDTO.getPassword()+"");
				
				System.out.println("result: " + result2);
				
				if(result2.equals(result1)) {
					return result2;
				}
			}
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
}

package com.yjb.business.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yjb.business.app.dto.RequestUserDto;
import com.yjb.business.app.entity.UserEntity;
//import com.yjb.business.app.form.RequestCntDto;
@Mapper
public interface UserMapper {
    public List<UserEntity> selectUser(@Param("id") String id, @Param("title") String title);
    public List<UserEntity> selectUserByPwd(@Param("userDto")RequestUserDto userDto);
//    public List<UserEntity> selectUserCnt(@Param("userCnt")RequestCntDto userCnt);
    public Integer selectUserCnt(@Param("userName")String userName);  
    public List<UserEntity> selectUserLimit(@Param("limit") Integer limit);
    public List<UserEntity> selectUserById(@Param("userDto")RequestUserDto userDto);
    public int insertUser(@Param("userDto")RequestUserDto userDto);
    public int insteUsers(@Param("id") int id);//01
    public int insteRoles(@Param("uid") int uid,@Param("rid") int rid);//02
    public int deleteRoles(@Param("uid") int uid,@Param("rid") int rid);//03
    public int deleteUsers(@Param("id") int id);//04
    public int updata(@Param("id") int id,
            @Param("email") String email,
            @Param("pwd") String pwd);//05
  //<insert id="insertBook">のidは必ずpublic int insertBookのinsertBookと同じにするように。
}
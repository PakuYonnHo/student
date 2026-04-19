package com.yjb.business.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yjb.business.app.dto.RequestUserRolesDto;	
@Mapper
public interface UserRolesMapper {
    public int insertUserRoles(@Param("userRolesDto")RequestUserRolesDto userRolesDto);
}
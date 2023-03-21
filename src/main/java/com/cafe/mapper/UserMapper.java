package com.cafe.mapper;


import com.cafe.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper  INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse  toUserResponse(User user);


    List<UserResponse> toUserResponseList(List<User> all);


}
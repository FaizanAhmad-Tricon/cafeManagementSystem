package com.cafe.mapper;


import com.cafe.entity.User;

import java.util.List;


public interface UserMapper {



    UserResponse  toUserResponse(User user);


     List<UserResponse> toUserResponseList(List<User> all);



}

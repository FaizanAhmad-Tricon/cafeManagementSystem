package com.cafe.mapper;


import com.cafe.entity.User;
import com.cafe.wrapper.CategoryRequest;

import java.util.List;


public interface UserMapper {



    CategoryRequest.UserResponse toUserResponse(User user);


     List<CategoryRequest.UserResponse> toUserResponseList(List<User> all);



}

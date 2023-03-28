package com.cafe.mapper;

import com.cafe.entity.User;
import com.cafe.wrapper.CategoryRequest;

import java.util.ArrayList;
import java.util.List;

public class UserMapperImp implements  UserMapper

{

    @Override
    public CategoryRequest.UserResponse toUserResponse(User user) {
         CategoryRequest.UserResponse userResponse = new CategoryRequest.UserResponse();
         userResponse.setId(user.getId());
         userResponse.setName(user.getName());
         userResponse.setStatus(user.getStatus());
         userResponse.setContactNumber(user.getContactNumber());
         userResponse.setRole(user.getRole());
         userResponse.setEmail(user.getEmail());
         return  userResponse;


    }

    @Override
    public List<CategoryRequest.UserResponse> toUserResponseList(List<User> all) {
        List<CategoryRequest.UserResponse>  userResponseList = new ArrayList<>();

        for (User user: all)
        {

            userResponseList.add(toUserResponse(user));



        }

        return  userResponseList;
    }
}

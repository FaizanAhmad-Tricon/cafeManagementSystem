package com.cafe.mapper;

import com.cafe.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapperImp implements  UserMapper

{

    @Override
    public UserResponse toUserResponse(User user) {
         UserResponse  userResponse = new UserResponse();
         userResponse.setId(user.getId());
         userResponse.setName(user.getName());
         userResponse.setStatus(user.getStatus());
         userResponse.setContactNumber(user.getContactNumber());
         userResponse.setRole(user.getRole());
         userResponse.setEmail(user.getEmail());
         return  userResponse;


    }

    @Override
    public List<UserResponse> toUserResponseList(List<User> all) {
        List<UserResponse>  userResponseList = new ArrayList<>();

        for (User user: all)
        {

            userResponseList.add(toUserResponse(user));



        }

        return  userResponseList;
    }
}

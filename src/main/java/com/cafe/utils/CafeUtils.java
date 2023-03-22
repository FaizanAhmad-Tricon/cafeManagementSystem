package com.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CafeUtils {


    private CafeUtils(){}

    public static ResponseEntity<String>  getResponseEntity(String responseMessag, HttpStatus httpStatus)
    {
        return new ResponseEntity<>("{\"message\" :\" "+ responseMessag+ "\"",  httpStatus);

    }






}

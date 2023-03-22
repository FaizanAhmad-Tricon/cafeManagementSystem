package com.cafe.mapper;


import com.cafe.entity.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.Map;


@Service

public interface CategoryMapper {



    Category tocategory(CategoryRequest request);


}

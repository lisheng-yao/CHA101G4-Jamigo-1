package com.jamigo.shop.category.dao;

import com.jamigo.shop.category.entity.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.List;


public interface Categorydao {

    List<CategoryVO> getAll();

    String addOne(CategoryVO categoryVO);

    String delete(Integer productCatNo);
}

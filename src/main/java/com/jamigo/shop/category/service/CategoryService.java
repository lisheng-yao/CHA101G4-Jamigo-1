package com.jamigo.shop.category.service;

import com.jamigo.shop.category.entity.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

List<CategoryVO> getAll();

String addOne(CategoryVO categoryVO);

String delete(Integer productCatNo);

}

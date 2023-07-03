package com.jamigo.shop.category.service.impl;

import com.jamigo.shop.category.dao.Categorydao;
import com.jamigo.shop.category.entity.CategoryVO;
import com.jamigo.shop.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CategoryServiceimpl implements CategoryService {

    @Autowired
    private Categorydao categorydao;

    @Override
    public List<CategoryVO> getAll() {
        return categorydao.getAll();
    }

    @Override
    public String addOne(CategoryVO categoryVO) {
        return categorydao.addOne(categoryVO);
    }

    @Override
    public String delete(Integer productCatNo) {
        return categorydao.delete(productCatNo);
    }
}

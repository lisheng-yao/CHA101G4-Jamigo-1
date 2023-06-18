package com.jamigo.promotion.PromotionType.Service.impl;

import com.jamigo.promotion.PromotionType.Dao.impl.PromotionTypeDaoImpl;
import com.jamigo.promotion.PromotionType.Entity.Promotion;
import com.jamigo.promotion.PromotionType.Service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromotionTypeServiceImpl implements PromotionTypeService {
    @Autowired
    private PromotionTypeDaoImpl Dao ;
    @Override
    public Promotion add(Promotion memberData) {
        return null;
    }


    @Override
    public Promotion edit(Promotion memberData) {
        return null;
    }

    @Override
    public List<Promotion> findAll() {
        return Dao.selectAll();
    }


    @Override
    public boolean remove(Integer id) {
        return false;
    }

}

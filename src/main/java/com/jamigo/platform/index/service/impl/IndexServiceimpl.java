package com.jamigo.platform.index.service.impl;


import com.jamigo.platform.index.dao.IndexDao;
import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.platform.index.service.IndexService;
import com.jamigo.shop.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class IndexServiceimpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public String insertOne(IndexVO indexVO) {
        return indexDao.insertOne(indexVO);
    }

    @Override
    public List<IndexVO> getAll() {
        return indexDao.getAll();
    }

    @Override
    public String deleteOne(Integer mainpageCarouselNo) {
        return indexDao.deleteOne(mainpageCarouselNo);
    }

    @Override
    public List<Product> getpopularproduct() {
        return indexDao.getpopularproduct();
    }

    @Override
    public List<IndexVO> getDuringTimeAll() {

        List<IndexVO> list = indexDao.getAll();
        List<IndexVO> filteredList = new ArrayList<>();

        for (IndexVO indexVO : list) {

            Date startTime = indexVO.getMainpageCarouselStartTime();
            Date endTime = indexVO.getMainpageCarouselEndTime();
            Date now = new Date();
            System.out.println(startTime);
            System.out.println(endTime);
            if (startTime.before(now) && endTime.after(now)) {
                filteredList.add(indexVO);
            }
        }


        return filteredList;
    }
}

package com.jamigo.platform.index.service.impl;


import com.jamigo.platform.index.dao.IndexDao;
import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.platform.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    @Override
//    public String deleteOne(Integer mainpageCarouselNo) {
//        return indexDao.?;
//    }
}

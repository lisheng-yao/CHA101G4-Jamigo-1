package com.jamigo.platform.index.dao;


import com.jamigo.platform.index.entity.IndexVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexDao {

    String insertOne(IndexVO indexVO);

    List<IndexVO> getAll();

    String deleteOne(Integer mainpageCarouselNo);

}

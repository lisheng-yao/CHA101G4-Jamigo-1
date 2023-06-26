package com.jamigo.platform.index.dao;


import com.jamigo.platform.index.entity.IndexVO;
import org.springframework.stereotype.Component;

@Component
public interface IndexDao {

    String insertOne(IndexVO indexVO);

}

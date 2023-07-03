package com.jamigo.platform.index.service;

import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.shop.product.entity.Product;

import java.util.List;

public interface IndexService {



String insertOne(IndexVO indexVO);

List<IndexVO> getAll();

String deleteOne(Integer mainpageCarouselNo);

List<Product> getpopularproduct();

List<IndexVO> getDuringTimeAll();

}

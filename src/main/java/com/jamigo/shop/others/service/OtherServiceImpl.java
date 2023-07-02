package com.jamigo.shop.others.service;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.shop.others.dto.ProductForMainPageDTO;
import com.jamigo.shop.others.repo.ProductForMainPageRepository;
import com.jamigo.shop.product.dto.ProductPageDTO;
import com.jamigo.shop.product.entity.Product;
import com.jamigo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherServiceImpl implements OtherService{

    @Autowired
    private ProductForMainPageRepository productForMainPageRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public List<ProductForMainPageDTO> getRecommendation() {

        List<Product> productList = productForMainPageRepository.getRandomProducts(5);

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (var product : productList) {

            ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
            productForMainPageDTO.setProductNo(product.getProductNo());
            productForMainPageDTO.setProductName(product.getProductName());
            productForMainPageDTO.setProductPrice(product.getProductPrice());
            productForMainPageDTO.setCounterNo(product.getCounterNo());

            counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

            productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
            productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

            productForMainPageDTOList.add(productForMainPageDTO);
        }

        return productForMainPageDTOList;
    }

    @Override
    public List<ProductForMainPageDTO> getCounterRecommendation(Integer counterNo) {
        List<Product> productList = productForMainPageRepository.getRandomCounterProducts(counterNo, 5);

        List<ProductForMainPageDTO> productForMainPageDTOList = new ArrayList<>();

        for (var product : productList) {

            ProductForMainPageDTO productForMainPageDTO = new ProductForMainPageDTO();
            productForMainPageDTO.setProductNo(product.getProductNo());
            productForMainPageDTO.setProductName(product.getProductName());
            productForMainPageDTO.setProductPrice(product.getProductPrice());
            productForMainPageDTO.setCounterNo(product.getCounterNo());

            counterRepository.findById(product.getCounterNo()).ifPresent(counter -> productForMainPageDTO.setCounterName(counter.getCounterName()));

            productForMainPageDTO.setEvalTotalPeople(product.getEvalTotalPeople());
            productForMainPageDTO.setEvalTotalScore(product.getEvalTotalScore());

            productForMainPageDTOList.add(productForMainPageDTO);
        }

        return productForMainPageDTOList;
    }
}

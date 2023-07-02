package com.jamigo.shop.others.controller;

import com.jamigo.shop.others.dto.ProductForMainPageDTO;
import com.jamigo.shop.others.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OtherController {

    @Autowired
    private OtherService otherService;

    @GetMapping("/shop/recommendation")
    public ResponseEntity<?> getRecommendation() {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getRecommendation();

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/shop/recommendation/counter/{counterNo}")
    public ResponseEntity<?> getCounterRecommendation(@PathVariable Integer counterNo) {

        List<ProductForMainPageDTO> productForMainPageDTOList = otherService.getCounterRecommendation(counterNo);

        if (productForMainPageDTOList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productForMainPageDTOList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

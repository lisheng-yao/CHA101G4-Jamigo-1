package com.jamigo.shop.category.controller;


import com.jamigo.shop.category.entity.CategoryVO;
import com.jamigo.shop.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryVO>> getAll(){

        List<CategoryVO> list = categoryService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/addOneCategory")
    public ResponseEntity<String> addOne(@RequestBody CategoryVO categoryVO){

       String message = categoryService.addOne(categoryVO);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/deleteOne/{productCatNo}")
    public ResponseEntity<String> delete(@PathVariable Integer productCatNo){

        String message = categoryService.delete(productCatNo);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


}

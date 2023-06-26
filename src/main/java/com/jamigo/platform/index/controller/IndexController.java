package com.jamigo.platform.index.controller;


import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.platform.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @Autowired
    IndexService indexService;


    @PostMapping("index/insert")
    public String insert(@RequestBody IndexVO indexVO){





        return indexService.insertOne(indexVO);
    }



}

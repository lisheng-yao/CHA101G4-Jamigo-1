package com.jamigo.platform.index.controller;


import com.jamigo.platform.index.entity.IndexVO;
import com.jamigo.platform.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {


    @Autowired
    IndexService indexService;


    @PostMapping("/index/insert")
    public String insert(@RequestBody IndexVO indexVO) {

        byte[] mainpageCarouselPic = indexVO.getMainpageCarouselPic();

        indexVO.setMainpageCarouselPic(mainpageCarouselPic);

        return indexService.insertOne(indexVO);
    }

    @GetMapping("/index/getAll")
    public ResponseEntity<List<IndexVO>> getAll() {

        List<IndexVO> list = indexService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/index/delete/{mainpageCarouselNo}")
    public ResponseEntity<Map> delete(@PathVariable Integer mainpageCarouselNo){
        System.out.println("近來");
        Map<String,Object> map = new HashMap<>();
        map.put("message",indexService.deleteOne(mainpageCarouselNo));

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}

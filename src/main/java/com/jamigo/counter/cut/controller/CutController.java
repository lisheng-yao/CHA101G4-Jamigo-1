package com.jamigo.counter.cut.controller;

import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.counter.cut.entity.Cut;
import com.jamigo.counter.cut.service.CutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CutController {

    @Autowired
    private CutService cutService;

    @GetMapping("/cut/all")
    public ResponseEntity<?> getAllCutData() {

        List<Cut> cutList = cutService.getAllCutData();

        if (cutList != null)
            return ResponseEntity.status(HttpStatus.OK).body(cutList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/cut/counter")
    public ResponseEntity<?> getAllCutData(HttpSession session) {

        Counter counter = (Counter) session.getAttribute("counter");

        List<Cut> cutList = cutService.getCounterAllCutData(counter.getCounterNo());

        if (cutList != null)
            return ResponseEntity.status(HttpStatus.OK).body(cutList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}

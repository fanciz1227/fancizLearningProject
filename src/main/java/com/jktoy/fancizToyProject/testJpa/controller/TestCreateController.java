package com.jktoy.fancizToyProject.testJpa.controller;

import com.jktoy.fancizToyProject.entity.TestCreateTableEntity;
import com.jktoy.fancizToyProject.testJpa.service.TestCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCreateController {

    @Autowired
    private TestCreateService testCreateService;

    @GetMapping("test")
    public String getCallTest() {
        List<TestCreateTableEntity> testString = testCreateService.testList();

        return testString.get(0).getCreateNm();
    }
}

package com.jktoy.fancizToyProject.testJpa.controller;

import com.jktoy.fancizToyProject.entity.TestCreateTable;
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
        List<TestCreateTable> testString = testCreateService.testGetList();

        return testString.get(0).getCreateNm();
    }

    @GetMapping("test/update")
    public void testCreate() {
        testCreateService.testCreate();
    }
}

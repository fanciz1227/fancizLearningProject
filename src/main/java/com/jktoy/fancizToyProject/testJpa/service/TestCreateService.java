package com.jktoy.fancizToyProject.testJpa.service;

import com.jktoy.fancizToyProject.entity.TestCreateTableEntity;
import com.jktoy.fancizToyProject.repository.TestCreateRepository;
import com.jktoy.fancizToyProject.testJpa.dto.TestCreateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCreateService {

    @Autowired
    private TestCreateRepository testCreateRepository;

    public List<TestCreateTableEntity> testList() {
        List<TestCreateTableEntity> list = testCreateRepository.findAll();

        return list;
    }
}

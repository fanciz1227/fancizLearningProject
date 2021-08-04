package com.jktoy.fancizToyProject.testJpa.service;

import com.jktoy.fancizToyProject.entity.TestCreateTableEntity;
import com.jktoy.fancizToyProject.repository.TestCreateRepository;
import com.jktoy.fancizToyProject.testJpa.dto.TestCreateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestCreateService {

    @Autowired
    private TestCreateRepository testCreateRepository;

    /**
     * jpa find Entity data
     * @return
     */
    public List<TestCreateTableEntity> testGetList() {
        List<TestCreateTableEntity> list = testCreateRepository.findAll();

        return list;
    }

    /**
     * jpa dirty checking
     */
    @Transactional
    public void testCreate() {
        TestCreateTableEntity findEntity = testCreateRepository.findById(1);

        findEntity.setCreateDesc("Update Test");
    }
}

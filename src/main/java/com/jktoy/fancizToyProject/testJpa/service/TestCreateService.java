package com.jktoy.fancizToyProject.testJpa.service;

import com.jktoy.fancizToyProject.entity.TestCreateTable;
import com.jktoy.fancizToyProject.repository.TestCreateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TestCreateService {

    @Autowired
    private TestCreateRepository testCreateRepository;

    /**
     * jpa find Entity data
     * @return
     */
    public List<TestCreateTable> testGetList() {
        List<TestCreateTable> list = testCreateRepository.findAll();

        return list;
    }

    /**
     * jpa dirty checking
     */
    @Transactional
    public void testCreate() {
        Optional<TestCreateTable> findEntity = testCreateRepository.findById(1);
    }
}

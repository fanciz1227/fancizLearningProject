package com.jktoy.fancizToyProject.repository;

import com.jktoy.fancizToyProject.entity.TestCreateTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCreateRepository extends JpaRepository<TestCreateTableEntity, Integer> {

    public List<TestCreateTableEntity> findAll();

    public TestCreateTableEntity findById(int id);
}

package com.jktoy.fancizToyProject.repository;

import com.jktoy.fancizToyProject.entity.TestCreateTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCreateRepository extends JpaRepository<TestCreateTable, Integer> {

}

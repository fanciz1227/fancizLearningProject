package com.jktoy.fancizToyProject.repository;

import com.jktoy.fancizToyProject.entity.TeamInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamInfoRepository extends JpaRepository<TeamInfo, Integer> {

}

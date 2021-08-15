package com.jktoy.fancizToyProject.repository;

import com.jktoy.fancizToyProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*@NamedQuery(
            name = "User.findByUserId",
            query = "SELECT us FROM User us"
    )
    public class User {};*/
}

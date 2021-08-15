package com.jktoy.fancizToyProject.jpa;

import com.jktoy.fancizToyProject.entity.User;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JooqTest {

    @Autowired
    private DSLContext dslContext;

    @Test
    public void jooqSelectTest() {
        List<User> userJooqList = dslContext.select(DSL.field("user_name"))
                .from(DSL.table("tb_user")).fetchInto(User.class);

        userJooqList.forEach(System.out::println);
    }
}

package com.jktoy.fancizToyProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_create_table")
@NoArgsConstructor
@Getter
public class TestCreateTableEntity {

    @Id
    @Column(name = "create_seq")
    private int createSeq;

    @Column(name = "create_nm")
    private String createNm;

    @Column(name = "create_desc")
    private String createDesc;
}

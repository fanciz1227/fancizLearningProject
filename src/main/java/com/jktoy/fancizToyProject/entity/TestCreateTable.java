package com.jktoy.fancizToyProject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_create_table")
@NoArgsConstructor
@Getter
public class TestCreateTable {

    @Id
    @Column(name = "create_seq")
    private int createSeq;

    @Column(name = "create_nm")
    private String createNm;

    @Column(name = "create_desc")
    private String createDesc;

    public void setCreateDesc(String createDesc){
        this.createDesc = createDesc;
    }
    public void setCreateNm(String createNm) { this.createNm = createNm; }
}

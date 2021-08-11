package com.jktoy.fancizToyProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_user")
@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private int userSeq;

    private String userName;

    private String userEmail;

    private String userPhoneNumber;

    @Column(name = "team_id")
    private int teamId;

    private LocalDateTime regDate;

    private LocalDateTime updtDate;
}

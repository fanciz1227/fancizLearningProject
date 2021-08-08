package com.jktoy.fancizToyProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_user")
@Entity
@NoArgsConstructor
@Getter
@ToString
public class User {

    @Id
    private int userSeq;

    private String userName;

    private String userEmail;

    private String userPhoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", insertable=false, updatable=false)
    private TeamInfo teamInfo;

    @Column(name = "team_id")
    private int teamId;

    private LocalDateTime regDate;

    private LocalDateTime updtDate;
}

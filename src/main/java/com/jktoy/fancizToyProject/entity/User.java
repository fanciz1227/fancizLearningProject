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
@NamedQuery(
        name = "User.findByUserSeq",
        query = "SELECT us FROM User us WHERE us.userSeq = :userSeq"
)
public class User {

    @Id
    @GeneratedValue
    private int userSeq;

    private String userName;

    private String userEmail;

    private String userPhoneNumber;

    @Column(name = "team_id")
    private int teamId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private TeamInfo teamInfo;

    private String userStatus;

    private LocalDateTime regDate;

    private LocalDateTime updtDate;
}

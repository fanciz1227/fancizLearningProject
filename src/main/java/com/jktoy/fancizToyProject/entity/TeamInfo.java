package com.jktoy.fancizToyProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "tb_team_info")
@Entity
@NoArgsConstructor
@Getter
public class TeamInfo {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_description")
    private String teamDescription;

    @OneToMany(mappedBy = "teamInfo")
    private List<User> userList;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "updt_date")
    private LocalDateTime updtDate;
}

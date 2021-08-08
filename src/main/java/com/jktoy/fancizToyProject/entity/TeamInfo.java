package com.jktoy.fancizToyProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_team_info")
@Entity
@NoArgsConstructor
@Getter
@ToString
public class TeamInfo {

    @Id
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_info")
    private String teamInfo;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "updt_date")
    private LocalDateTime updtDate;
}

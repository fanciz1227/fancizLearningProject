package com.jktoy.fancizToyProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_team_info")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TeamInfo {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_description")
    private String teamDescription;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "updt_date")
    private LocalDateTime updtDate;

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }
}

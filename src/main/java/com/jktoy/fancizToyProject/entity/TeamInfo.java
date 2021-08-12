package com.jktoy.fancizToyProject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_team_info")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class TeamInfo {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_description")
    private String teamDescription;

    @Column(name = "team_phone")
    private String teamPhone;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "updt_date")
    private LocalDateTime updtDate;

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }
}

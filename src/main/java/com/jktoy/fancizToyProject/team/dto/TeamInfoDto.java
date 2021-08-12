package com.jktoy.fancizToyProject.team.dto;

import com.jktoy.fancizToyProject.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamInfoDto {

    private int teamId;
    private String teamName;
    private String teamDescription;
    private String teamPhone;

    private User user;

    @Builder
    public TeamInfoDto(int teamId, String teamName, String teamDescription, String teamPhone, User user) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamPhone = teamPhone;
        this.user = user;
    }
}

package com.jktoy.fancizToyProject.user.dto;

import com.jktoy.fancizToyProject.entity.TeamInfo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserDto {
    private int userSeq;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private int teamId;
    private TeamInfo teamInfo;

    @QueryProjection
    public UserDto(int userSeq, String userName, String userEmail, String userPhoneNumber,
                   int teamId, TeamInfo teamInfo) {
        this.userSeq = userSeq;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.teamId = teamId;
        this.teamInfo = teamInfo;
    }
}

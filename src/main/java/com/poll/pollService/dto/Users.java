package com.poll.pollService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Users {

    public String email;
    public String vote;

    public Users(String email, String vote) {
        this.email = email;
        this.vote = vote;
    }
}

package com.poll.pollService.repository;

import com.poll.pollService.dto.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Getter
@Setter
public class UserRepository {

    private List<Users> usersList;
    private String email;


}

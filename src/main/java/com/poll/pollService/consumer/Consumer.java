package com.poll.pollService.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.pollService.dto.EmailDTO;
import com.poll.pollService.dto.Users;
import com.poll.pollService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private List<Users> userList;

    public Consumer() {
        this.userList = new ArrayList<>();
    }


    public List<Users> getUserList() {
        return userList;
    }

    @JmsListener(destination = "fila1")
    public void receiveMessage(String message) throws JmsException, JsonProcessingException {

        log.info("vote computed " + message);
        log.info("next check" + userRepository.getEmail());


       if(userRepository.getEmail() != null){
           userList.add(new Users(userRepository.getEmail(), message));

        }

    }
}


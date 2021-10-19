package com.poll.pollService.consumer;

import com.poll.pollService.emailSender.EmailServiceImpl;
import com.poll.pollService.utils.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Consumer {

    private String email;
    private List<Users> userList;

    public Consumer() {
        this.userList = new ArrayList<>();
    }

    public void saveEmail(String email){

        this.email = email;
    }

    public List<Users> getUserList() {
        return userList;
    }

    @JmsListener(destination = "fila1")
    public void receiveMessage(String message) throws JmsException {

        log.info("vote computed " + message);
        log.info("next check" + email);


       if(email != null){
           userList.add(new Users(email, message));

        }

    }
}


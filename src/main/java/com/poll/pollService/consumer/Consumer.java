package com.poll.pollService.consumer;

import com.poll.pollService.emailSender.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    private String email;
    private EmailServiceImpl emailService;

    @Autowired
    public Consumer(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    public void saveEmail(String email){

        this.email = email;
    }

    @JmsListener(destination = "fila1")
    public void receiveMessage(String message) throws JmsException {

        log.info("Message Received: " + message);

        try{
            if (message.equals("VOTE_REJECTING")) {

                emailService.sendSimpleMessage(email,
                                              "Email Teste",
                                              "Email direto da minha java app");
            }

            if (message.equals("VOTE_APPROVING")) {

                emailService.sendSimpleMessage(email,
                                            "Email Teste",
                                            "Email direto da minha java app");
            }

        }catch(Exception e){

            log.info("it did not return a compatible message");
        }

    }
}


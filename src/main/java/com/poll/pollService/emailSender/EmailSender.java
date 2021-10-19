package com.poll.pollService.emailSender;

import com.poll.pollService.consumer.Consumer;
import com.poll.pollService.utils.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class EmailSender {

    @Autowired
    Consumer consumer;

    @Autowired
    EmailServiceImpl emailService;

    //@Scheduled(cron = "0 0/15 * * * *") //15minutos
    @Scheduled(cron = "0 * * ? * *")// 1 minute / for test
    public void sendEmail(){

        for(Users curr : consumer.getUserList()){

            String message = curr.getVote();
            String email = curr.getEmail();
            try{
                if (message.equals("VOTE_REJECTING")) {

                emailService.sendSimpleMessage(email,
                        "Poll is Over",
                        "The poll that you voted 'NO' is over");
                }

                if (message.equals("VOTE_APPROVING")) {

                    emailService.sendSimpleMessage(email,
                            "Poll is Over",
                            "The poll that you voted 'YES' is over");
                }


            }catch(NullPointerException ex){

            log.info("no one voted");
            }

        }
    }


}

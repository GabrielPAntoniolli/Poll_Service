package com.poll.pollService.JmsPublisher;

import com.poll.pollService.consumer.Consumer;
import com.poll.pollService.utils.UserDecision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subscription")
@Slf4j
public class PublisherController {

    @Autowired
    Consumer consumer;
    UserDecision decision;

    @Autowired
    JmsTemplate jmsTemplate;


    @PostMapping()
    public String getEmail(@RequestBody String email){

        consumer.saveEmail(email);
        return "Email saved.";
    }

    @GetMapping("/approved")
    public String voteApproving(){

        jmsTemplate.convertAndSend("fila1",decision.VOTE_APPROVING);
        return "You approved this project";
    }

    @GetMapping("/rejecting")
    public String voteRejecting(){

        jmsTemplate.convertAndSend("fila1",decision.VOTE_REJECTING);
        return "You rejected this project";
    }


}

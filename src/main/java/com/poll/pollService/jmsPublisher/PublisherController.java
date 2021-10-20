package com.poll.pollService.jmsPublisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.pollService.consumer.Consumer;
import com.poll.pollService.dto.EmailDTO;
import com.poll.pollService.repository.UserRepository;
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
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    UserDecision decision;

    @Autowired
    JmsTemplate jmsTemplate;

    //get email before anything else
    @PostMapping()
    public String getEmail(@RequestBody EmailDTO email) throws JsonProcessingException {

       // String map = objectMapper.writeValueAsString(email.getAddress());
        this.userRepository.setEmail(objectMapper.writeValueAsString(email.getAddress()));
        return "Email saved.";
    }

    //display topic to the user to decide yes or not
    @GetMapping("/topic")
    public String displayTopic(){

        //think a Post system for this later
        return "vocé eh a favor de excluir o inter de competicoes nacionais?";

    }

    @GetMapping("/topic/approved")
    public String voteApproving(){

        jmsTemplate.convertAndSend("fila1",decision.VOTE_APPROVING);
        return "You voted yes";
    }

    @GetMapping("/topic/rejecting")
    public String voteRejecting(){

        jmsTemplate.convertAndSend("fila1",decision.VOTE_REJECTING);
        return "You voted no";
    }


}

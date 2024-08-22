package com.example.message.message.functions;

import com.example.message.message.dto.LeaveMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<LeaveMessageDto, LeaveMessageDto> email(){
        return leaveMessageDto -> {
            logger.info("Sending EMAIL with details " + leaveMessageDto);
            return leaveMessageDto;
        };
    }

//    @Bean
//    public Function<LeaveMessageDto, Long> sms(){
//        return leaveMessageDto -> {
//            logger.info("Sending SMS with details " + leaveMessageDto);
//            return leaveMessageDto.getEmployeeId();
//        };
//    }
}

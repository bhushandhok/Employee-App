package com.bhushantdhok.employee.app.service;

import com.bhushantdhok.employee.app.utils.MessageUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Service;

@Service
class MessageService implements IMessageService {

    private final MessageSource messageSource;

    MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public String getMessage(String key) {
        return MessageUtil.getMessage(messageSource, key);
    }

    @Override
    public String getMessage(String key, String... args) {
        return MessageUtil.getMessage(messageSource, key, args);
    }

    @Override
    public String getMessage(MessageSourceResolvable error) {
        return MessageUtil.getMessage(messageSource, error);
    }


}

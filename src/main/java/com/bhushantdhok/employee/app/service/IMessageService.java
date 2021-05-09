package com.bhushantdhok.employee.app.service;

import org.springframework.context.MessageSourceResolvable;

public interface IMessageService {

    String getMessage(String key);
    String getMessage(String key, String... args);
    String getMessage(MessageSourceResolvable error);
}
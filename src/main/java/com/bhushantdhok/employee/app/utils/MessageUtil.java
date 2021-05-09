package com.bhushantdhok.employee.app.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MessageUtil {
    public static String getMessage(MessageSource messageSource, String key) {
        return messageSource.getMessage(key, null, "", getLocale());
    }


    public static String getMessage(MessageSource messageSource, String key, String... args) {
        return messageSource.getMessage(key, args, "", getLocale());
    }



    public static String getMessage(MessageSource messageSource, MessageSourceResolvable error){
        try {
            return messageSource.getMessage(error, getLocale());
        }catch (NoSuchMessageException ex){
            return "";
        }
    }

    public static Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
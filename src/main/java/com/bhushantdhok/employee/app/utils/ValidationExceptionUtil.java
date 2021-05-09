package com.bhushantdhok.employee.app.utils;


import com.bhushantdhok.employee.app.error.ValidationError;
import com.bhushantdhok.employee.app.service.IMessageService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ValidationExceptionUtil {
    public static ValidationError processFieldErrors(IMessageService messageService, List<FieldError> fieldErrors) {
        ValidationError validationErrorDto = new ValidationError();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedFieldErrorMessage(messageService, fieldError);
            validationErrorDto.addError(fieldError.getField(), localizedErrorMessage);
        }
        return validationErrorDto;
    }


    public static String resolveLocalizedFieldErrorMessage(IMessageService messageService, FieldError fieldError) {
        String localizedErrorMessage = messageService.getMessage(fieldError);
        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
        return localizedErrorMessage;
    }

    public static final List<String> processGlobalErrors(IMessageService messageService, List<ObjectError> objectErrors) {
        List<String> errors = new ArrayList<>();
        for (ObjectError objectError : objectErrors) {
            String localizedErrorMessage = resolveLocalizedObjectErrorMessage(messageService, objectError);
            errors.add(localizedErrorMessage);
        }
        return errors;
    }

    public static String resolveLocalizedObjectErrorMessage(IMessageService messageService, ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageService.getMessage(objectError);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(objectError.getDefaultMessage())) {
            String[] objectErrorCodes = objectError.getCodes();
            localizedErrorMessage = objectErrorCodes[0];
        }

        return localizedErrorMessage;
    }

}
package com.bhushantdhok.employee.app.error;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.*;

public class ValidationError {
    Map<String, Set<String>> errors = new HashMap<>();
    List<String> mandatoryFields = new ArrayList<>();
    List<String> masterErrorFields = new ArrayList<>();


    public static ValidationError getInstance() {
        return new ValidationError();
    }


    @JsonAnySetter
    public ValidationError addError(String path, String message) {
        Set<String> messages = null;
        if (errors.containsKey(path)) {
            messages = errors.get(path);

        } else {
            messages = new HashSet<>();
            errors.put(path, messages);
        }
        messages.add(message);
        return this;
    }


    public ValidationError addErrors(String path, Set<String> moreMessages) {
        Set<String> messages = null;
        if (errors.containsKey(path)) {
            messages = errors.get(path);

        } else {
            messages = new HashSet<>();
            errors.put(path, messages);
        }
        messages.addAll(moreMessages);
        return this;
    }

    public ValidationError addErrors(Map<String, Set<String>> errors) {
        if (errors != null) {
            this.errors.putAll(errors);
        }
        return this;
    }

    public ValidationError addMasterErrorFields(String field){
        if (field != null)
            masterErrorFields.add(field);
        return this;
    }

    public ValidationError addMandatoryFields(String field){
        if (field != null)
            mandatoryFields.add(field);
        return this;
    }

    @JsonAnyGetter
   public Map<String, Set<String>> getErrors() {
        return errors;
    }


}
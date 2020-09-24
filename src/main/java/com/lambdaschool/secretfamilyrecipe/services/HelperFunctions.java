package com.lambdaschool.secretfamilyrecipe.services;

import com.lambdaschool.secretfamilyrecipe.models.ValidationError;

import java.util.List;

public interface HelperFunctions {

    List<ValidationError> getConstraintViolation(Throwable cause);

    boolean isAuthorizedToMakeChanges(String username);
}

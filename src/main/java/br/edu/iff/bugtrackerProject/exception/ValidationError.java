package br.edu.iff.bugtrackerProject.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ValidationError extends Error{
    
    private List<PropertyError> errors = new ArrayList<>();
    
    public ValidationError(Calendar timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<PropertyError> getErrors() {
        return errors;
    }

    public void setErrors(List<PropertyError> errors) {
        this.errors = errors;
    }
    
}

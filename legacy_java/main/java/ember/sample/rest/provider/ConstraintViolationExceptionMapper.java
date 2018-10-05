package ember.sample.rest.provider;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonRootName;

import ember.sample.rest.messages.ErrorList;
import ember.sample.rest.messages.ViolationError;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(ConstraintViolationException ex) {
        MediaType type = headers.getMediaType();
        Locale locale = headers.getLanguage();

        Object responseObject = getConstraintViolationErrors(ex);

        Response.ResponseBuilder builder = Response.ok(responseObject, type);
        builder.status(422);
        builder.language(locale);
        return builder.build();
    }

    public static Object getConstraintViolationErrors(ConstraintViolationException ex) {
        List<ViolationError> errors = new ArrayList<>();

        for (@SuppressWarnings("rawtypes")
        ConstraintViolation violation : ex.getConstraintViolations()) {
            ViolationError error = new ViolationError();
            error.setMessage(violation.getMessage());
            error.setInvalidValue(violation.getInvalidValue());
            error.setPropertyPath(violation.getPropertyPath().toString());
            Class<?> clazz = violation.getRootBeanClass();
            JsonRootName rootName = clazz.getAnnotation(JsonRootName.class);
            error.setRootBeanClass(rootName != null ? rootName.value() : clazz.getSimpleName().toLowerCase());
            errors.add(error);
        }

        Object responseObject = new ErrorList<>(errors);
        return responseObject;
    }

}
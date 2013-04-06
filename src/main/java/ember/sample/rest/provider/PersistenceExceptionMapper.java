package ember.sample.rest.provider;


import java.util.Arrays;
import java.util.Locale;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ember.sample.rest.messages.ErrorList;
import ember.sample.rest.messages.Message;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    final static Logger logger = LoggerFactory.getLogger(PersistenceExceptionMapper.class);

    @Context
    private HttpHeaders headers;

    public Response toResponse(PersistenceException ex) {
        logger.error("Error on persist data", ex);

        MediaType type = headers.getMediaType();
        Locale locale = headers.getLanguage();

        Object responseObject = null;
        if (ex.getCause() instanceof ConstraintViolationException) {
            responseObject = ConstraintViolationExceptionMapper.getConstraintViolationErrors((ConstraintViolationException) ex.getCause());
        } else {
            Message error = new Message("Error");
            error.setMessage(ex.getMessage());
            responseObject = new ErrorList<>(Arrays.asList(error));
        }

        Response.ResponseBuilder builder = Response.ok(responseObject, type);
        builder.status(422);
        builder.language(locale);
        return builder.build();
    }
}

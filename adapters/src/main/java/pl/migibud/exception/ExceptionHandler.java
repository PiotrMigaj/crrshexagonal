package pl.migibud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.migibud.organisation.OrganisationError;
import pl.migibud.organisation.OrganisationException;

import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OrganisationException.class)
    ResponseEntity<ErrorInfo> handleAppUserException(OrganisationException e){
        HttpStatus httpStatus = null;
        if (OrganisationError.ORGANISATION_NOT_FOUND.equals(e.getOrganisationError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (OrganisationError.ORGANISATION_ALREADY_EXISTS.equals(e.getOrganisationError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getOrganisationError().getMessage())));
    }
}

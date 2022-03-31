package rs.ac.uns.ftn.isa.pharmacy.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.AllergyException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.QuantityException;

@ControllerAdvice
public class PersistenceExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String entityNotFoundHandler(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String entityAlreadyExistsHandler(EntityAlreadyExistsException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String clientAppointmentHandler(UserAccessException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(QuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String storedDrugQuantityHandler(QuantityException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String dateHandler(DateException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AllergyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String allergyHandler(AllergyException e){
        return e.getMessage();
    }

}

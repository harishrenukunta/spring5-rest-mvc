package guru.springframework.controllers;

import guru.springframework.ExceptionHandler.NoVendorsException;
import guru.springframework.ExceptionHandler.ResourceNotFoundException;
import guru.springframework.ExceptionHandler.VendorNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleExceptionHandler(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<Object> vendorNotFoundExceptionHandler(final VendorNotFoundException ex, final WebRequest request){
        final Map<String, String> map = new LinkedHashMap();
        map.put("time", LocalDateTime.now().toString());
        map.put("errorText", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoVendorsException.class)
    public ResponseEntity<Object> noVendorExceptionHandler(final NoVendorsException ex, final WebRequest request){
        final Map<String, String> map = new LinkedHashMap();
        map.put("time", LocalDateTime.now().toString());
        map.put("errorText", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

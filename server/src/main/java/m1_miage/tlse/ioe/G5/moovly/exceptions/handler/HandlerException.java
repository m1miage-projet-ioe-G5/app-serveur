package m1_miage.tlse.ioe.G5.moovly.exceptions.handler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import m1_miage.tlse.ioe.G5.moovly.errors.BadRequestErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.errors.CreationFailedErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.errors.ForbiddenErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.errors.NotFoundErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.BadRequestRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.CreationFailedRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.ForbiddenRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.NotFoundRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import m1_miage.tlse.ioe.G5.moovly.errors.*;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(CreationFailedRestException.class)
    public ResponseEntity<CreationFailedErrorResponse> handleCreationFailed(HttpServletRequest httpServletRequest, Exception e){
        CreationFailedRestException exception = (CreationFailedRestException) e;
        final CreationFailedErrorResponse response = CreationFailedErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(exception.getMessage())
                .build();
        HandlerException.log.warn(exception.getMessage());
       return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(BadRequestRestException.class)
    public ResponseEntity<BadRequestErrorResponse>
    handleBadRequest(HttpServletRequest httpServletRequest, Exception e){
        BadRequestRestException exception = (BadRequestRestException) e;
        final BadRequestErrorResponse response = BadRequestErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(exception.getMessage())
                .build();
        HandlerException.log.warn(exception.getMessage());
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NotFoundRestException.class)
    public ResponseEntity<NotFoundErrorResponse>
    handlerNotFoundRequest(HttpServletRequest httpServletRequest, Exception e){
        NotFoundRestException exception = (NotFoundRestException) e;
        final NotFoundErrorResponse response = NotFoundErrorResponse
                .builder()
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(exception.getMessage())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }
    @ExceptionHandler(ForbiddenRestException.class)
    public ResponseEntity<ForbiddenErrorResponse> handleForbiddenRequest(HttpServletRequest httpServletRequest, Exception e){
        ForbiddenRestException exception = (ForbiddenRestException) e;
        final ForbiddenErrorResponse response = ForbiddenErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(GettingFailedRestException.class)
    public ResponseEntity<GettingFailedErrorResponse> handleGettingRequest(HttpServletRequest httpServletRequest,Exception e){
        GettingFailedRestException exception = (GettingFailedRestException) e;
        final GettingFailedErrorResponse response = GettingFailedErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(500).body(response);
    }
}


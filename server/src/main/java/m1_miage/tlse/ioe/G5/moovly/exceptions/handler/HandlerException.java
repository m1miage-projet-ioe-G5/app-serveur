package m1_miage.tlse.ioe.G5.moovly.exceptions.handler;

//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import m1_miage.tlse.ioe.G5.moovly.errors.BadRequestErrorResponse;
//import m1_miage.tlse.ioe.G5.moovly.errors.CreationFailedErrorResponse;
//import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.BadRequestRestException;
//import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.CreationFailedRestException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@Slf4j
//@ControllerAdvice
//public class HandlerException {
//
//    @ExceptionHandler(CreationFailedRestException.class)
//    public ResponseEntity<CreationFailedErrorResponse> handleCreationFailed(HttpServletRequest httpServletRequest, Exception e){
//        CreationFailedRestException exception = (CreationFailedRestException) e;
//        final CreationFailedErrorResponse response = CreationFailedErrorResponse
//                .builder()
//                .uri(httpServletRequest.getRequestURI())
//                .errorMessage(exception.getMessage())
//                .build();
//        log.warn(exception.getMessage());
//        return ResponseEntity.status(500).body(response);
//    }
//
//    @ExceptionHandler(BadRequestRestException.class)
//    public ResponseEntity<BadRequestErrorResponse>
//    handleBadRequest(HttpServletRequest httpServletRequest, Exception e){
//        BadRequestRestException exception = (BadRequestRestException) e;
//        final BadRequestErrorResponse response = BadRequestErrorResponse
//                .builder()
//                .uri(httpServletRequest.getRequestURI())
//                .errorMessage(exception.getMessage())
//                .build();
//        log.warn(exception.getMessage());
//        return ResponseEntity.status(400).body(response);
//    }
//}

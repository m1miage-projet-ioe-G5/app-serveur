package m1_miage.tlse.ioe.G5.moovly.exceptions.rest;

public class ForbiddenRestException extends RuntimeException{
    public ForbiddenRestException(String message){
        super(message);
    }
}
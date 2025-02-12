package m1_miage.tlse.ioe.G5.moovly.exceptions.technical;

import lombok.Getter;

@Getter
public class UserNotFoundRestException extends Exception{
    public UserNotFoundRestException(String message){
        super(message);

    }

}

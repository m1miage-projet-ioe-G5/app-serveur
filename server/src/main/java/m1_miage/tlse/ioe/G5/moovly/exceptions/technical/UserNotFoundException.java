package m1_miage.tlse.ioe.G5.moovly.exceptions.technical;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}

package m1_miage.tlse.ioe.G5.moovly.exceptions.technical;

import lombok.Getter;

@Getter
public class LieuNotFoundException extends Exception {
  public LieuNotFoundException(String message) {
    super(message);
  }
}

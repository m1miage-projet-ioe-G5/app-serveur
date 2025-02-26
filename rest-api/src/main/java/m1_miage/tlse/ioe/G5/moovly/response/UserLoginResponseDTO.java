package m1_miage.tlse.ioe.G5.moovly.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Présentation de la connexion d'un utilisateur")
public class UserLoginResponseDTO {
    @Schema(description = "Identifiant d'un utilisateur")
    private String email;
    @Schema(description = "Mot de passe de l'utilisateur")
    private String motDePasse;
    @Schema(description = "Token ")
    private String token;


}

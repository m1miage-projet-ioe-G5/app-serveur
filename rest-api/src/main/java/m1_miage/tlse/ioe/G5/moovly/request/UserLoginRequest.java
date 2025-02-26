package m1_miage.tlse.ioe.G5.moovly.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Corps de la requête d'authentification d'un utilisateur")
public class UserLoginRequest {
    @Schema(description = "email de l'utilisateur")
    private String email;
    @Schema(description = "Mot de passe de l'utilisateur")
    private String motDePass;
}

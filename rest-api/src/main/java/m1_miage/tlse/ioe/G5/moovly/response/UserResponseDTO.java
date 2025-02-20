package m1_miage.tlse.ioe.G5.moovly.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Présentation d'un utilisateur")
public class UserResponseDTO {
    @Schema(description = "Identifiant d'un utilisateur")
    private String email;
    @Schema(description = "nom de l'utilisateur")
    private String nom;
    @Schema(description = "prenom de l'utilisateur")
    private String prenom;
   @Schema(description = "Mot de passe de l'utilisateur")
    private String motDePasse;

}

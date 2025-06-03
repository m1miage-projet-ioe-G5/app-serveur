package m1_miage.tlse.ioe.G5.moovly.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeMobilite;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Corps de la requête de création d'un utilisateur")
public class UserCreationRequest {
    @Schema(description = "identiant de l'utilisateur")
    private String email;
    @Schema(description = "nom de l'utilisateur")
    private String nom;
    @Schema(description = "Prénom de l'utilisateur")
    private String prenom;
    @Schema(description = "Mot de pass de l'utilisateur")
    private String motDePasse;
    @Schema(description = "id de firebase")
    private String firebaseUid;

    // rajouté par Khalil
    @Schema(description = "type de mobilité de l'utilisateur")
    private TypeMobilite typeMobilite ;
}

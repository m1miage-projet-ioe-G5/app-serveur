package m1_miage.tlse.ioe.G5.moovly.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "création d'un utilisateur")
public class UserCreationResponseDTO {
    @Schema(description = "echec ou reussite de la création de l'utilisateur")
    private boolean success;
    @Schema(description = "message d'echec ou de succes de création d'un utilisateur")
    private String message;
}

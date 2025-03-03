package m1_miage.tlse.ioe.G5.moovly.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserNotFoundErrorResponse {

    @Schema(description = "end point call", example = "/api/v1/user")
    private final String uri ;

    @Schema(description = "error message", example = "L'utilisateur M n'existe pas")
    private final String errorMessage ;
}

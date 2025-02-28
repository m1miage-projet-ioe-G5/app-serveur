package m1_miage.tlse.ioe.G5.moovly.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignalementNotFoundErrorResponse {

    @Schema(description = "end point call", example = "/api/v1/reports")
    private final String uri ;

    @Schema(description = "error message", example = "Le signalement S n'existe pas")
    private final String errorMessage ;
}

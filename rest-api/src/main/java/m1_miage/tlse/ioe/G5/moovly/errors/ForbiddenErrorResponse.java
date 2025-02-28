package m1_miage.tlse.ioe.G5.moovly.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ForbiddenErrorResponse {
    @Schema(description = "end point call", example = "/api/v1/")
    private final String uri;
    @Schema(description = "error message", example = "L'utilisateur n'existe pas")
    private final String errorMessage;
}

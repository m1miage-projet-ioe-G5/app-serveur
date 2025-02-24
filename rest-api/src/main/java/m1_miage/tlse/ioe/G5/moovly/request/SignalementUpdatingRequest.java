package m1_miage.tlse.ioe.G5.moovly.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeProbleme;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalementUpdatingRequest {

    @Schema(description = "état du signalement")
    private EtatSignalement etatSignalement ;

    @Schema(description = "nature du problème")
    private TypeProbleme typeProbleme ;

    @Schema(description = "description du problème")
    private String description ;

    @Schema(description = "photo d'illustration")
    private String photoUrl ;
}

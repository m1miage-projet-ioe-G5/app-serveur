package m1_miage.tlse.ioe.G5.moovly.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeProbleme;
import java.time.LocalDateTime;

@Data
@Schema(description = "Représente un signalement")
public class SignalementResponseDTO {

    @Schema(description = "id du signalement")
    private Long id ;

    @Schema(description = "longitude du lieu de signalement")
    private Double longitude ;

    @Schema(description = "latitude du lieu de signalement")
    private Double latitude ;

    @Schema(description = "état du signalement")
    private EtatSignalement etatSignalement ;

    @Schema(description = "nature du problème")
    private TypeProbleme typeProbleme ;

    @Schema(description = "description du problème")
    private String description ;

    @Schema(description = "photo d'illustration")
    private String photoUrl ;

    @Schema(description = "date de la création du signalement")
    private LocalDateTime dateCreation ;

    @Schema(description = "L'utilisateur qui a signalé l'incident")
    private UserResponseDTO user ;
}

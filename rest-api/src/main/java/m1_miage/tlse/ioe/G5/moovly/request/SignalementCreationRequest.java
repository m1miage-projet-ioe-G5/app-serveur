package m1_miage.tlse.ioe.G5.moovly.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeProbleme;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignalementCreationRequest {

    @Schema(description = "id du signalement")
    private Long id ;

    @Schema(description = "longitude du lieu de signalement")
    private Double longitude ;

    @Schema(description = "latitude du lieu de signalement")
    private Double latitude ;

    @Schema(description = "nature du problème")
    private TypeProbleme typeProbleme ;

    @Schema(description = "description du problème")
    private String description ;

    @Schema(description = "photo d'illustration")
    private String photoUrl ;

    @Schema(description = "date de la création du signalement")
    private LocalDateTime dateCreation ;

    @Schema(description = "email de l'utilisateur qui signale")
    private String emailUser ;

    @Schema(description = "identifiant du lieu, s'il s'agit d'un lieu")
    private String idLieu ;

    @Schema(description = "identifiant de l'itinéraire, s'il s'agit d'un itinéraire")
    private Long idItineraire ;
}

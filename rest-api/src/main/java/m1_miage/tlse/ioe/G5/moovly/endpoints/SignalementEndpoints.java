package m1_miage.tlse.ioe.G5.moovly.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import m1_miage.tlse.ioe.G5.moovly.errors.*;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementUpdatingRequest;
import m1_miage.tlse.ioe.G5.moovly.response.SignalementResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion signalement", description = "Tous les endpoints de gestion d'un signalement")
@RestController
@RequestMapping("/api/v1/reports")
public interface SignalementEndpoints {

    @Operation(description = "Création d'un signalement")
    @ApiResponse(responseCode = "201", description = "Le signalement a été créé avec succès")
    @ApiResponse(   responseCode = "400",
                    description = "Requête invalide",
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    @ApiResponse(   responseCode = "500",
                    description = "Echec de la création du signalement",
                    content = @Content(schema = @Schema(implementation = CreationFailedErrorResponse.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    SignalementResponseDTO createSignalement(@RequestBody SignalementCreationRequest signalementCreationRequest) ;


    @Operation(description = "Récupère la liste des signalements ")
    @ApiResponse(responseCode = "200", description = "Liste des signalements envoyée avec succès")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<SignalementResponseDTO> getSignalements() ;


    @Operation(description = "Met à jour un signalement")
    @ApiResponse(responseCode = "200", description = "Mise à jour de signalement réussie")
    @ApiResponse(   responseCode = "400",
                    description = "Requête invalide",
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    @ApiResponse(   responseCode = "404",
                    description  = "Signalement non trouvé",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "500",
                    description = "Echec de la mise à jour du signalement",
                    content = @Content(schema = @Schema(implementation = UpdatingFailedErrorResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{idSignalement}")
    SignalementResponseDTO updateSignalement(@PathVariable(name = "idSignalement") Long idSignalement, @RequestBody SignalementUpdatingRequest signalementUpdatingRequest) ;


    @Operation(description = "Supprime un signalement")
    @ApiResponse(responseCode = "200", description = "Le signalement a été supprimé avec succès")
    @ApiResponse(   responseCode = "400",
                    description = "Requête invalide",
                    content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class)))
    @ApiResponse(   responseCode = "404",
                    description  = "Signalement non trouvé",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))

    @ApiResponse(   responseCode = "500",
                    description = "Echec de la suppression du signalement",
                    content = @Content(schema = @Schema(implementation = DeletingFailedErrorResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{idSignalement}")
    void deleteSignalementById(@PathVariable(name = "idSignalement") Long idSignalement) ;
}

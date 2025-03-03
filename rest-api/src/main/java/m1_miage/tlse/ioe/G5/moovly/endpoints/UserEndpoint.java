package m1_miage.tlse.ioe.G5.moovly.endpoints;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import m1_miage.tlse.ioe.G5.moovly.errors.BadRequestErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.errors.CreationFailedErrorResponse;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public interface UserEndpoint {

    @ApiResponse(responseCode = "201", description = "Création réussie")
    @ApiResponse(responseCode = "400" ,
            description = "Requête invalide",
            content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),
                    mediaType = MediaType.
                            APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "500",
            description = "Echec de la création d'un utilisateur",
            content = @Content(schema = @Schema(implementation = CreationFailedErrorResponse.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403",description = "Accès refusé",
        content = @Content(schema =  @Schema(implementation = ForbiddenErrorResponse.class),
        mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    UserResponseDTO createUser(@RequestBody UserCreationRequest userCreationRequest);

    @ApiResponse(responseCode = "200", description = "Récuperation réussie")
    @ApiResponse(responseCode = "400",description = "Requête invalide",
        content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),
        mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé",
        content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",description = "Aucun utilisateur trouvé",
        content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Problème de récupération",
        content = @Content(schema = @Schema(implementation = GettingFailedErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<UserResponseDTO> getAllUsers();

    @ApiResponse(responseCode = "200", description = "Récuperation réussie")
    @ApiResponse(responseCode = "400",description = "Requête invalide",
            content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé",
            content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404",description = "Aucun utilisateur trouvé",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Problème de récupération",
            content = @Content(schema = @Schema(implementation = GettingFailedErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    UserResponseDTO getUserByEmail(@PathVariable String email);

    @ApiResponse(responseCode = "200", description = "L'utilisateur a été supprimé avec succès")
    @ApiResponse(   responseCode = "400",
            description = "Requête invalide",
            content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "404",
            description  = "L'utilisateur non trouvé",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "500",
            description = "Echec de la suppression du l'utilisateur",
            content = @Content(schema = @Schema(implementation = DeletingFailedErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable(name = "email") String email);

    @Operation(description = "Renvoie le nombre d’incidents signalés par un utilisateur")
    @ApiResponse(   responseCode = "200", description = "Nombre de signalements renvoyé avec succès")
    @ApiResponse(   responseCode = "400",
            description = "Requête invalide",
            content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "404",
            description  = "Utilisateur non trouvé",
            content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(   responseCode = "500",
            description = "Echec de la recupération du nombre d'incidents",
            content = @Content(schema = @Schema(implementation = GettingFailedErrorResponse.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}/reports/total")
    int getNumberOfReportsByEmailUser(@PathVariable(name = "email") String email) ;
}
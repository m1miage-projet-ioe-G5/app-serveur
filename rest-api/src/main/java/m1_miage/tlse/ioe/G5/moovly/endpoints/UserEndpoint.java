package m1_miage.tlse.ioe.G5.moovly.endpoints;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "500", description = "Problème de création")
    @ApiResponse(responseCode = "400" ,
            description = "L'utilisateur n'a pas été créer",
            content = @Content(schema = @Schema(implementation = String.class),
                    mediaType = MediaType.
            APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    UserResponseDTO createUser(@RequestBody UserCreationRequest userCreationRequest);


    @ApiResponse(responseCode = "201", description = "Récuperation réussie")
    @ApiResponse(responseCode = "500", description = "Problème de récupération")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<UserResponseDTO> getAllUsers();

    @ApiResponse(responseCode = "200", description = "Récuperation réussie")
    @ApiResponse(responseCode = "500", description = "Problème de récupération")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    UserResponseDTO getUserByEmail(@PathVariable String email);

    @ApiResponse(responseCode = "200",description ="Suppression réussie")
    @ApiResponse(responseCode = "500", description ="Probleme de suppression")
    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable(name = "email") String email);



}


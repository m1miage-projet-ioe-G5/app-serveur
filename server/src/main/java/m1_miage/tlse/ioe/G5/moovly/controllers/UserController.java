package m1_miage.tlse.ioe.G5.moovly.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.components.UserComponent;
import m1_miage.tlse.ioe.G5.moovly.endpoints.UserEndpoint;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.BadRequestRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.UserNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.UserLoginRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserLoginResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserEndpoint {
    private final UserService userService;

    @Override
    public UserResponseDTO createUser(UserCreationRequest userCreationRequest) {
        return userService.create(userCreationRequest);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userService.deleteUserByEmail(email);
    }


    /**
     *
     * @param email l'email de l'utilisateur à obtenir ses signalements
     * @return le nombre des signalements de l'utilisateur
     */
    @Override
    public int getNumberOfReportsByEmailUser(String email) {
        return userService.getNumberOfReportsByEmailUser(email) ;
    }
}

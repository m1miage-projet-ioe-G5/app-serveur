package m1_miage.tlse.ioe.G5.moovly.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.endpoints.UserEndpoint;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserEndpoint {
    private final UserService userService;
    @Override
    public UserResponseDTO createUser(UserCreationRequest userCreationRequest) {
        return userService.create(userCreationRequest);
    }

//    @Override
//    public UserResponseDTO getAllUsers(UserResponseDTO userResponseDTO) {
//        return null;
//    }
}

package m1_miage.tlse.ioe.G5.moovly.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.endpoints.UserEndpoint;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.services.UserService;
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

    /**
     * @return
     */
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAllUsers();
    }
    /**
     * @param email
     * @return
     */
    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public void deleteUserByEmail(String email) {
        userService.deleteUserByEmail(email);
    }


}

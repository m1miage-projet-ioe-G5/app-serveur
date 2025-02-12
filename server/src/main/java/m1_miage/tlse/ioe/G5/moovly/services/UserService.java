package m1_miage.tlse.ioe.G5.moovly.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import m1_miage.tlse.ioe.G5.moovly.components.UserComponent;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.BadRequestRestException;
import m1_miage.tlse.ioe.G5.moovly.mappers.UserMapper;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserComponent userComponent;
    private final  UserMapper userMapper;

// Création du service
    public UserResponseDTO create(UserCreationRequest userCreationRequest) {
        try {
            UserEntity userEntity = userMapper.toEntity(userCreationRequest);
            return userMapper.toUserResponseDTO(userComponent.createUser(userEntity));
        } catch (Exception e) {
            throw new BadRequestRestException(e.getMessage());
        }
    }
}

package m1_miage.tlse.ioe.G5.moovly.mappers;

import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserCreationRequest userCreationRequest) {
        if (userCreationRequest == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setNumero(userCreationRequest.getNumero());
        userEntity.setNom (userCreationRequest.getNom());
        userEntity.setPrenom(userCreationRequest.getPrenom());
        return userEntity;
    }

    public UserResponseDTO toUserResponseDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setNumero(userEntity.getNumero());
        responseDTO.setNom(userEntity.getNom());
        responseDTO.setPrenom(userEntity.getPrenom());
        return responseDTO;
    }
}
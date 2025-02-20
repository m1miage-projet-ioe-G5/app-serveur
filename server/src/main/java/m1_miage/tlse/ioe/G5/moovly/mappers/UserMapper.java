package m1_miage.tlse.ioe.G5.moovly.mappers;

import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
public interface UserMapper {
    UserResponseDTO toUserResponseDTO(UserEntity userEntity);
    UserEntity toEntity(UserCreationRequest userCreationRequest);
    List<UserResponseDTO> toUserResponseDTO(List<UserEntity> userEntities);

}
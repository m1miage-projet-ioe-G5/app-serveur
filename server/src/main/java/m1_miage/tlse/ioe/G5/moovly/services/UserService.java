package m1_miage.tlse.ioe.G5.moovly.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import m1_miage.tlse.ioe.G5.moovly.components.UserComponent;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.BadRequestRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.DeletedFailedRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.GettingFailedRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.NotFoundRestException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.UserNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.mappers.UserMapper;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserResponseDTO> findAllUsers(){
        try {
            List<UserEntity> userEntities = userComponent.getAlls();
            return userMapper.toUserResponseDTO(userEntities);
        } catch (Exception e) {
            throw  new NotFoundRestException(e.getMessage());
        }
    }
    public UserResponseDTO findUserByEmail(String email){
        try {
            UserEntity userEntity = userComponent.getByemail(email);
            return userMapper.toUserResponseDTO(userEntity);
        } catch (UserNotFoundException | NotFoundRestException e) {
            throw new NotFoundRestException(e.getMessage());
        }
    }
    public void deleteUserByEmail(String email){
        try {
            userComponent.deleteUser(email);
        } catch (UserNotFoundException e) {
            throw new BadRequestRestException(e.getMessage());
        } catch (DeletedFailedRestException e) {
            throw new DeletedFailedRestException(e.getMessage());
        }
    }
    public int getNumberOfReportsByEmailUser(String email) {
        try {
            UserEntity userEntity = userComponent.getByemail(email) ;
            return userEntity.getNbIncidentsReportes() ;
        } catch (UserNotFoundException e) {
            throw new NotFoundRestException(e.getMessage()) ;
        }
        catch (BadRequestRestException e) {
            throw new BadRequestRestException(e.getMessage()) ;
        }
        catch (GettingFailedRestException e) {
            throw new GettingFailedRestException(e.getMessage()) ;
        }
    }
}

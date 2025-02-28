package m1_miage.tlse.ioe.G5.moovly.components;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.UserNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserComponent {
    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAlls(){
        return userRepository.findAll();
    }

    public UserEntity getByemail(String email) throws UserNotFoundException {
        return userRepository.findById(email).orElseThrow(()
                -> new UserNotFoundException(String
                .format("User non trouver par le :[%s] ",email)));
    }

    public void deleteUser(String email) throws UserNotFoundException{
       userRepository.findById(email).orElseThrow(()->
               new UsernameNotFoundException("Utilisateur non trouvé par: [%s]"));
       userRepository.deleteById(email);
    }
}

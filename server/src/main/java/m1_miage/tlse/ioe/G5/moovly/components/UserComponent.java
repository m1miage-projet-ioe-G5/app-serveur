package m1_miage.tlse.ioe.G5.moovly.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserComponent {
    private final UserRepository userRepository;
    public UserEntity createUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}

package m1_miage.tlse.ioe.G5.moovly.services;

import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.NotFoundRestException;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    void createUserSucces(){
        UserCreationRequest userRequest = UserCreationRequest.builder()
                .email("martin@gmail.com")
                .motDePasse("testMPD")
                .nom("Martin")
                .build();
        UserResponseDTO resultat = userService.create(userRequest);
        assertEquals(userRequest.getEmail(), resultat.getEmail());
        assertNotNull(resultat);
        assertEquals("Martin", resultat.getNom());
    }
    @Test
    void findAllUsersFound(){
        UserCreationRequest user1 = UserCreationRequest.builder()
                .email("leo@gmail.com")
                .motDePasse("testMPD")
                .nom("Leonard")
                .build();
        UserCreationRequest user2 = UserCreationRequest.builder()
                .email("martine@gmail.com")
                .motDePasse("testMPD")
                .nom("Lionel")
                .build();
        UserResponseDTO userDTO1 = userService.create(user1);
        UserResponseDTO userDTO2 = userService.create(user2);
        List<UserResponseDTO> users = userService.findAllUsers();
        assertEquals(3, users.size());

        assertTrue(users.stream().anyMatch(u ->u.getEmail().equals("leo@gmail.com")));
        assertTrue(users.stream().anyMatch(u ->u.getEmail().equals("martine@gmail.com")));
        assertEquals(userDTO2.getEmail(), user2.getEmail());
        assertEquals(userDTO1.getEmail(), user1.getEmail());

    }
    @Test
    void findUserByMail(){
        UserCreationRequest userRequest = UserCreationRequest.builder()
                .email("leo@gmail.com")
                .motDePasse("testMPD")
                .nom("Leonard")
                .build();
        UserResponseDTO user = userService.create(userRequest);
        UserResponseDTO resultat = userService.findUserByEmail("leo@gmail.com");
        assertNotNull(resultat);
        assertEquals(resultat.getEmail(), userRequest.getEmail());
        assertEquals(user.getEmail(), resultat.getEmail());
    }
    @Test
    void deleteUserByMail(){
        UserCreationRequest newUser = UserCreationRequest.builder()
                .email("diallo@gmail.com")
                .motDePasse("diallo23")
                .nom("Fello")
                .build();
        UserResponseDTO userSaved = userService.create(newUser);
        userService.deleteUserByEmail(userSaved.getEmail());
        assertThrows(NotFoundRestException.class, () -> {
            userService.findUserByEmail(userSaved.getEmail());
        });    }


}

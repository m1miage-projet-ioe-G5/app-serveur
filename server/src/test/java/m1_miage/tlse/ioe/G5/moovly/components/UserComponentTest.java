package m1_miage.tlse.ioe.G5.moovly.components;

import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.UserNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import m1_miage.tlse.ioe.G5.moovly.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserComponentTest {
    @Autowired
    private UserComponent userComponent;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        // GIVEN
        UserEntity newUser = UserEntity.builder()
                .nom("BARRY")
                .prenom("Fello")
                .email("fello@gmail.com")
                .motDePasse("notblank")
                .build();
        // WHEN
        UserEntity userSaved = userComponent.createUser(newUser);
        // THEN
        assertNotNull(userSaved);
        assertEquals(userSaved.getNom(), newUser.getNom());
        assertEquals(userSaved.getPrenom(), newUser.getPrenom());
        assertEquals(userSaved.getEmail(), newUser.getEmail());
    }
    @Test
    public void findAllUsersTest(){
        userRepository.deleteAll();
        //GIVEN
        UserEntity userEntity1 = UserEntity.builder()
                .email("barry@gmail.com")
                .prenom("Martin")
                .nom("Martine")
                .motDePasse("Test")
                .dateCreation(LocalDate.now())
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .email("martine@gmail.com")
                .prenom("Aurelie")
                .nom("Dupont")
                .motDePasse("Ytest2")
                .dateCreation(LocalDate.now())
                .build();
      // Enrégitrement des utilisateurs dans la base de données
        userRepository.saveAll(List.of(userEntity1, userEntity2));
        //WHEN appel de la ethode a tester
        List<UserEntity> usersTrouves = userComponent.getAlls();
        //THEN -verification
        assertEquals(2,usersTrouves.size(),"Devrait retourner 2 utilisateurs");
        // Vérification plus approfondue
        assertTrue(usersTrouves.stream().anyMatch(u->u.getEmail().equals("barry@gmail.com")));
        assertTrue(usersTrouves.stream().anyMatch(u->u.getEmail().equals("martine@gmail.com")));

    }
    @Test
    void getByMailNotFound() {
        // GIVEN
        // WHEN/THEN
        assertThrows(UserNotFoundException.class,
                () -> userComponent.getByemail("testEmail"));
    }
    @Test
    void getByMailFound() throws UserNotFoundException {
        UserEntity user1 = UserEntity.builder()
                .email("barry@gmail.com")
                .nom("Fello")
                .motDePasse("_hosting")
                .build();
        userRepository.save(user1);

        UserEntity userSaved = userComponent.getByemail(user1.getEmail());
        assertEquals(userSaved.getEmail(), user1.getEmail());
    }
    @Test
    void deletetUser() throws UserNotFoundException {
        UserEntity userEntity = UserEntity.builder()
                .prenom("TestUser")
                .email("hello@gmail.com")
                .motDePasse("test")
                .build();
        userRepository.save(userEntity);
        userComponent.deleteUser(userEntity.getEmail());
        assertFalse(userRepository.existsById(userEntity.getEmail()));
    }
    @Test
    void deleteUserNotFound(){
        assertThrows(UserNotFoundException.class,
                () -> userComponent.deleteUser("notfound@gmail.com"));
    }

}
package m1_miage.tlse.ioe.G5.moovly.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import m1_miage.tlse.ioe.G5.moovly.request.UserCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.response.UserResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
@ActiveProfiles("test")
@Transactional
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @LocalServerPort
    private int port;

    private final HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Transactional
    void canCreateUser() {
        // Given
        final UserCreationRequest request = UserCreationRequest
                .builder()
                .email("test@example.com")
                .nom("Doe")
                .prenom("John")
                .motDePasse("securePassword123")
                .firebaseUid("firebase-uid-123")
                .build();

        // When
        ResponseEntity<UserResponseDTO> response = testRestTemplate
                .exchange("/v1/user/create",
                        HttpMethod.POST,
                        new HttpEntity<>(request, headers),
                        UserResponseDTO.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(userRepository.count()).isEqualTo(1);
        assertThat(response.getBody())
                .extracting("email", "nom", "prenom")
                .containsExactly("test@example.com", "Doe", "John");
    }
    @Test
    void getAllUsers() {
        // Given
        UserEntity user1 = UserEntity.builder()
                .email("user1@example.com")
                .nom("Dupont")
                .prenom("Jean")
                .motDePasse("securePass123!")
                .build();
        UserEntity user2 = UserEntity.builder()
                .email("user2@example.com")
                .nom("Martin")
                .prenom("Marie")
                .motDePasse("anotherSecurePass!")
                .build();
        // Sauvegarde avec vérification
        List<UserEntity> savedUsers = userRepository.saveAll(List.of(user1, user2));
        assertThat(savedUsers).hasSize(2);

        // When - Exécution de la requête
        ResponseEntity<List<UserResponseDTO>> response = testRestTemplate.exchange(
                "/v1/user/",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<UserResponseDTO>>() {});

        // Then - Vérifications complètes
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @Transactional
    void getUserByEmail() {
        // Given
        final UserCreationRequest request1 = UserCreationRequest
                .builder()
                .email("ibra@gmail.com")
                .nom("Doe")
                .prenom("John")
                .motDePasse("securePassword123")
                .firebaseUid("fpasswor")
                .build();
        userService.create(request1);
        assertEquals("ibra@gmail.com",request1.getEmail());

        // When
        ResponseEntity<UserResponseDTO> response = testRestTemplate.exchange(
                "/v1/user/{email}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserResponseDTO.class,
                "ibra@gmail.com"
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .extracting("email", "nom", "prenom")
                .containsExactly("dalas@gmail.com", "Doe", "John");
    }
    @Test
    void deleteUserByEmail() {
        // Given
        UserEntity user = UserEntity.builder()
                .email("todelete@example.com")
                .nom("ToDelete")
                .prenom("User")
                .motDePasse("password")
                .build();
        userRepository.save(user);
        // When
        String url = "http://localhost:" + port + "/v1/user/todelete@example.com";
        ResponseEntity<Void> response = testRestTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Void.class
        );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findById ("todelete@example.com")).isEmpty();
    }

}
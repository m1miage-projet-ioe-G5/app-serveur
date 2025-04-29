package m1_miage.tlse.ioe.G5.moovly.services;

import m1_miage.tlse.ioe.G5.moovly.components.SignalementComponent;
import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.SignalementNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.mappers.SignalementMapper;
import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.UserRepository;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementUpdatingRequest;
import m1_miage.tlse.ioe.G5.moovly.response.SignalementResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SignalementServiceTest {
    @Autowired
    private SignalementService signalementService;
    @MockBean
    private SignalementComponent signalementComponent;
    @SpyBean
    private SignalementMapper signalementMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void createSignalement(){
        // Given
        UserEntity userEntity = UserEntity
                .builder()
                .email("user@gmail.com")
                .build();

        SignalementCreationRequest signalementCreationRequest = SignalementCreationRequest
                .builder()
                .id(1L)
                .emailUser("user@gmail.com")
                .build();

        userRepository.save(userEntity);
        SignalementEntity signalementEntity = signalementMapper.signalementRequestToSignalementEntity(signalementCreationRequest);

        // When
        when(signalementComponent.createSignalement(any(SignalementEntity.class))).thenReturn(signalementEntity);
        SignalementResponseDTO expectedResponse = signalementMapper.signalementEntityToSignalementResponseDTO(signalementEntity);
        SignalementResponseDTO response = signalementService.createSignalement(signalementCreationRequest);

        // Then
        assertThat(response).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void getSignalements(){
        // Given
        SignalementEntity signalement1 = SignalementEntity
                .builder()
                .id(1L)
                .build();

        SignalementEntity signalement2 = SignalementEntity
                .builder()
                .id(2L)
                .build();

        List<SignalementEntity> signalementEntities = new ArrayList<>();
        signalementEntities.add(signalement1);
        signalementEntities.add(signalement2);

        when(signalementComponent.getSignalements()).thenReturn(signalementEntities);

        // When
        List<SignalementResponseDTO> responseDTOS = signalementService.getSignalements();

        // Then
        assertNotNull(responseDTOS);
        assertEquals(responseDTOS.size(), 2);
    }

    @Test
    void updateSignalement() throws SignalementNotFoundException {
        // Given
        SignalementEntity signalement = SignalementEntity
                .builder()
                .id(1L)
                .etatSignalement(EtatSignalement.CREE)
                .description("ancienne description")
                .build();

        SignalementUpdatingRequest updatingRequest = SignalementUpdatingRequest
                .builder()
                .etatSignalement(EtatSignalement.RESOLUE)
                .description("nouvelle description")
                .build();

        SignalementEntity updatedSignalement = SignalementEntity
                .builder()
                .id(1L)
                .etatSignalement(updatingRequest.getEtatSignalement())
                .description(updatingRequest.getDescription())
                .build();

        SignalementResponseDTO responseDTO = signalementMapper.signalementEntityToSignalementResponseDTO(updatedSignalement);

        when(signalementComponent.getSignalementById(signalement.getId())).thenReturn(signalement);
        when(signalementComponent.updateSignalement(signalement)).thenReturn(updatedSignalement);
        when(signalementMapper.signalementEntityToSignalementResponseDTO(updatedSignalement)).thenReturn(responseDTO);

        // When
        SignalementResponseDTO result = signalementService.updateSignalement(signalement.getId(), updatingRequest);

        // Then
        assertEquals(responseDTO, result);
        verify(signalementMapper).updateSignalementEntityFromRequest(updatingRequest, signalement);
    }

    @Test
    void deleteSignalementByIdWhenSignalementExists() throws SignalementNotFoundException {
        // Given
        SignalementEntity signalement = SignalementEntity
                .builder()
                .id(1L)
                .build();
        doNothing().when(signalementComponent).deleteSignalementById(1L);

        // Then - When
        assertDoesNotThrow(()->signalementService.deleteSignalementById(1L));
        verify(signalementComponent, times(1)).deleteSignalementById(1L);
    }
}

package m1_miage.tlse.ioe.G5.moovly.components;

import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.SignalementNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.SignalementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SignalementComponentTest {
    @Autowired
    private SignalementComponent signalementComponent ;
    @MockBean
    private SignalementRepository signalementRepository ;

    @Test
    void getSignalementByIdNotFound(){
        // Given
        when(signalementRepository.findById(anyLong())).thenReturn(Optional.empty());

        // then - when
        assertThrows(SignalementNotFoundException.class, ()->signalementComponent.getSignalementById(999L));
    }

    @Test
    void getSignalementByIdFound(){
        // Given
        SignalementEntity signalementEntity = SignalementEntity.builder()
                .id(1L)
                .build() ;
        when(signalementRepository.findById(anyLong())).thenReturn(Optional.of(signalementEntity)) ;

        // when - then
        assertDoesNotThrow(()->signalementComponent.getSignalementById(1L));
    }

    @Test
    void getSignalementsEmpty(){
        // Given
        List<SignalementEntity> emptySignalements = new ArrayList<>() ;
        when(signalementRepository.findAll()).thenReturn(emptySignalements) ;

        // When
        List<SignalementEntity> result = signalementComponent.getSignalements();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void getSignalementsNotEmpty(){
        // Given
        SignalementEntity signalement1 = SignalementEntity.builder()
                .id(1L)
                .build();

        SignalementEntity signalement2 = SignalementEntity.builder()
                .id(2L)
                .build();

        List<SignalementEntity> expectedSignalements = new ArrayList<>() ;
        expectedSignalements.add(signalement1);
        expectedSignalements.add(signalement2);
        when(signalementRepository.findAll()).thenReturn(expectedSignalements);

        // When
        List<SignalementEntity> result = signalementComponent.getSignalements();

        // Then
        assertEquals(expectedSignalements.size(), result.size(), "La taille doit être égale à 2");
        assertTrue(result.containsAll(expectedSignalements), "Doit contenir tous les signalements");
    }

    @Test
    void createSignalement(){
        // Given
        SignalementEntity signalement = SignalementEntity.builder()
                .id(1L)
                .build();

        when(signalementRepository.save(any())).thenReturn(signalement);

        // When
        SignalementEntity result = signalementComponent.createSignalement(signalement);

        // Then
        assertEquals(result, signalement);
        verify(signalementRepository, times(1)).save(any());
    }

    @Test
    void updateSignalement() {
        // Given
        SignalementEntity signalement = SignalementEntity
                .builder()
                .id(1L)
                .etatSignalement(EtatSignalement.CREE)
                .description("ancienne description")
                .build();
        when(signalementRepository.save(signalement)).thenReturn(signalement);

        // When
        signalement.setEtatSignalement(EtatSignalement.RESOLUE);
        signalement.setDescription("nouvelle description");

        SignalementEntity result = signalementComponent.updateSignalement(signalement);

        // Then
        assertEquals(signalement.getId(), result.getId());
        assertEquals(signalement.getEtatSignalement(), result.getEtatSignalement());
        assertEquals(signalement.getDescription(), result.getDescription());
        verify(signalementRepository, times(1)).save(signalement);
    }

    @Test
    void deleteSignalementByIdWhenElementExists(){
        // Given
        Long id = 1L;
        SignalementEntity signalement = SignalementEntity
                .builder()
                .id(id)
                .build() ;

        when(signalementRepository.findById(id)).thenReturn(Optional.of(signalement));
        doNothing().when(signalementRepository).deleteById(id);

        // Then - when
        assertDoesNotThrow(()->signalementComponent.deleteSignalementById(id));
        verify(signalementRepository, times(1)).findById(id);
        verify(signalementRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteSignalementByIdWhenElementNotFound(){
        // Given
        Long id = 999L ;
        when(signalementRepository.findById(id)).thenReturn(Optional.empty());

        // Then - When
        assertThrows(SignalementNotFoundException.class, ()->signalementComponent.deleteSignalementById(id));
        verify(signalementRepository, times(1)).findById(id);
        verify(signalementRepository, never()).deleteById(anyLong());
    }
}

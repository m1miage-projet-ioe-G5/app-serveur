package m1_miage.tlse.ioe.G5.moovly.components;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.SignalementNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.SignalementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SignalementComponent {

    private final SignalementRepository signalementRepository ;

    public SignalementEntity createSignalement(SignalementEntity signalementEntity) {
        return signalementRepository.save(signalementEntity) ;
    }

    public List<SignalementEntity> getSignalements() {
       return signalementRepository.findAll() ;
    }

    public SignalementEntity getSignalementById(Long idSignalement) throws SignalementNotFoundException {
        return signalementRepository.findById((idSignalement))
                .orElseThrow(() -> new SignalementNotFoundException(String.format("Signalement [%s] non trouvé", idSignalement))) ;
    }

    public SignalementEntity updateSignalement(SignalementEntity signalementEntity) {
        return signalementRepository.save(signalementEntity) ;
    }

    public void deleteSignalementById(Long idSignalement) throws SignalementNotFoundException {
        signalementRepository.findById(idSignalement)
                .orElseThrow(() -> new SignalementNotFoundException(String.format("Signalement [%s] non trouvé", idSignalement))) ;
        signalementRepository.deleteById(idSignalement) ;
    }
}

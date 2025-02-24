package m1_miage.tlse.ioe.G5.moovly.components;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.LieuNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.LieuEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.LieuRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LieuComponent {

    private final LieuRepository lieuRepository ;

    public LieuEntity getLieuById(String idLieu) throws LieuNotFoundException {
        return lieuRepository.findById(idLieu)
                .orElseThrow(() -> new LieuNotFoundException(String.format("Lieu [%s] non trouvée", idLieu))) ;
    }
}

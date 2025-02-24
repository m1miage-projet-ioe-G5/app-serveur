package m1_miage.tlse.ioe.G5.moovly.components;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.ItineraireNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.models.ItineraireEntity;
import m1_miage.tlse.ioe.G5.moovly.repositories.ItineraireRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItineraireComponent {

    private final ItineraireRepository itineraireRepository ;

    public ItineraireEntity getItineraireById(Long idItineraire) throws ItineraireNotFoundException {
        return itineraireRepository.findById(idItineraire)
                .orElseThrow(() -> new ItineraireNotFoundException(String.format("Itinéraire [%s] non trouvée", idItineraire))) ;
    }
}

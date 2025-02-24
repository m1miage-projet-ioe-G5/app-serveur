package m1_miage.tlse.ioe.G5.moovly.controllers;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.endpoints.SignalementEndpoints;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementUpdatingRequest;
import m1_miage.tlse.ioe.G5.moovly.response.SignalementResponseDTO;
import m1_miage.tlse.ioe.G5.moovly.services.SignalementService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SignalementController implements SignalementEndpoints {

    private final SignalementService signalementService ;

    @Override
    public SignalementResponseDTO createSignalement(SignalementCreationRequest signalementCreationRequest) {
        return signalementService.createSignalement(signalementCreationRequest) ;
    }

    @Override
    public List<SignalementResponseDTO> getSignalements() {
        return signalementService.getSignalements() ;
    }

    @Override
    public SignalementResponseDTO updateSignalement(Long idSignalement, SignalementUpdatingRequest signalementUpdatingRequest) {
        return signalementService.updateSignalement(idSignalement, signalementUpdatingRequest) ;
    }

    @Override
    public void deleteSignalementById(Long idSignalement) {
        signalementService.deleteSignalementById(idSignalement); ;
    }
}

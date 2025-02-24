package m1_miage.tlse.ioe.G5.moovly.services;

import lombok.RequiredArgsConstructor;
import m1_miage.tlse.ioe.G5.moovly.components.ItineraireComponent;
import m1_miage.tlse.ioe.G5.moovly.components.LieuComponent;
import m1_miage.tlse.ioe.G5.moovly.components.SignalementComponent;
import m1_miage.tlse.ioe.G5.moovly.components.UserComponent;
import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.exceptions.rest.*;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.ItineraireNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.LieuNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.SignalementNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.exceptions.technical.UserNotFoundException;
import m1_miage.tlse.ioe.G5.moovly.mappers.SignalementMapper;
import m1_miage.tlse.ioe.G5.moovly.models.ItineraireEntity;
import m1_miage.tlse.ioe.G5.moovly.models.LieuEntity;
import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementUpdatingRequest;
import m1_miage.tlse.ioe.G5.moovly.response.SignalementResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignalementService {

    private final SignalementComponent signalementComponent ;
    private final UserComponent userComponent ;
    private final LieuComponent lieuComponent ;
    private final ItineraireComponent itineraireComponent ;
    private final SignalementMapper signalementMapper ;

    public SignalementResponseDTO createSignalement(SignalementCreationRequest signalementCreationRequest) {
        try {
            SignalementEntity signalementEntity = signalementMapper.signalementRequestToSignalementEntity(signalementCreationRequest) ;
            signalementEntity.setEtatSignalement(EtatSignalement.CREE);
            UserEntity userEntity = userComponent.getByemail(signalementCreationRequest.getEmailUser()) ;
            signalementEntity.setUserEntity(userEntity);
            if (signalementCreationRequest.getIdLieu() != null) {
                LieuEntity lieuEntity = lieuComponent.getLieuById(signalementCreationRequest.getIdLieu()) ;
                signalementEntity.setLieuEntity(lieuEntity);
            }
            if (signalementCreationRequest.getIdItineraire() != null) {
                ItineraireEntity itineraireEntity = itineraireComponent.getItineraireById(signalementCreationRequest.getIdItineraire()) ;
                signalementEntity.setItineraireEntity(itineraireEntity);
            }
            return signalementMapper.signalementEntityToSignalementResponseDTO(signalementComponent.createSignalement(signalementEntity)) ;
        }
        catch (UserNotFoundException | LieuNotFoundException | ItineraireNotFoundException e) {
            throw new NotFoundRestException(e.getMessage());
        }
        catch (BadRequestRestException e) {
            throw new BadRequestRestException(e.getMessage());
        }
        catch (CreationFailedRestException e) {
            throw new CreationFailedRestException(e.getMessage());
        }
    }

    public List<SignalementResponseDTO> getSignalements() {
        try {
            return signalementMapper.signalementEntitiesToSignalementResponseDTOs(signalementComponent.getSignalements()) ;
        } catch (Exception e) {
            throw new NotFoundRestException(e.getMessage());
        }
    }

    public SignalementResponseDTO updateSignalement(Long idSignalement, SignalementUpdatingRequest signalementUpdatingRequest) {
        try {
            SignalementEntity currentSignalement = signalementComponent.getSignalementById(idSignalement) ;
            signalementMapper.updateSignalementEntityFromRequest(signalementUpdatingRequest, currentSignalement);
            SignalementEntity newSignalement = signalementComponent.updateSignalement(currentSignalement) ;
            return signalementMapper.signalementEntityToSignalementResponseDTO(newSignalement) ;
        }
        catch (SignalementNotFoundException e) {
            throw new NotFoundRestException(e.getMessage()) ;
        } catch (BadRequestRestException e) {
            throw new BadRequestRestException(e.getMessage()) ;
        }
        catch (UpdatingFailedRestException e) {
            throw new UpdatingFailedRestException(e.getMessage());
        }
    }

    public void deleteSignalementById(Long idSignalement) {
        try {
            signalementComponent.deleteSignalementById(idSignalement) ;
        } catch (SignalementNotFoundException e) {
            throw new NotFoundRestException(e.getMessage());
        }
        catch (BadRequestRestException e) {
            throw new BadRequestRestException(e.getMessage());
        }
        catch (DeletedFailedRestException e) {
            throw new DeletedFailedRestException(e.getMessage());
        }
    }
}

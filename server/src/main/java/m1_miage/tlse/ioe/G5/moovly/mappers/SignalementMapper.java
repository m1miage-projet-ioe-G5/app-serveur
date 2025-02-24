package m1_miage.tlse.ioe.G5.moovly.mappers;

import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementCreationRequest;
import m1_miage.tlse.ioe.G5.moovly.request.SignalementUpdatingRequest;
import m1_miage.tlse.ioe.G5.moovly.response.SignalementResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface SignalementMapper {

    SignalementEntity signalementRequestToSignalementEntity(SignalementCreationRequest signalementCreationRequest) ;

    List<SignalementResponseDTO> signalementEntitiesToSignalementResponseDTOs(List<SignalementEntity> signalementEntities) ;

    @Mapping(source = "userEntity", target = "user")
    SignalementResponseDTO signalementEntityToSignalementResponseDTO(SignalementEntity signalementEntity) ;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSignalementEntityFromRequest(SignalementUpdatingRequest signalementUpdatingRequest, @MappingTarget SignalementEntity signalementEntity) ;
}

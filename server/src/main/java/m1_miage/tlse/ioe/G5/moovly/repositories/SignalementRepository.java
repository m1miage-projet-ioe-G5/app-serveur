package m1_miage.tlse.ioe.G5.moovly.repositories;

import m1_miage.tlse.ioe.G5.moovly.models.SignalementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalementRepository extends JpaRepository<SignalementEntity,String> {
}

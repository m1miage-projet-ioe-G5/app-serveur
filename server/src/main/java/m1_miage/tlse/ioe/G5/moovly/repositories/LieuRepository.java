package m1_miage.tlse.ioe.G5.moovly.repositories;

import m1_miage.tlse.ioe.G5.moovly.models.LieuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieuRepository extends JpaRepository<LieuEntity, String> {
}

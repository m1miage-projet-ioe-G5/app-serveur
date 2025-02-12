package m1_miage.tlse.ioe.G5.moovly.repositories;

import m1_miage.tlse.ioe.G5.moovly.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}

package m1_miage.tlse.ioe.G5.moovly.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
        @Id
        private String numero;
        @Column( updatable = true)
        private String nom;
        @Column
        private String prenom;

        @Column(unique = true)
        private String email;
        @Column
        private LocalDate dateCreation;
        @Column
        private String motDePasse;
        @Column
        private boolean notificationsEnabled;

        @OneToMany(mappedBy = "userEntity")
        private Set<ItineraireEntity> itineraireEntities;

        @OneToMany(mappedBy = "userEntity")
        private Set<SignalementEntity> signalementEntities;
}

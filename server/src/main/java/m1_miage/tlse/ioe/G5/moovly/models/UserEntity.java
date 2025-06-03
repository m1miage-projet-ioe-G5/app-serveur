package m1_miage.tlse.ioe.G5.moovly.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeMobilite;

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
    @Column(nullable = false)
    private String email;
    private String nom;
    @Column
    private String prenom;

    @Column
    private LocalDate dateCreation;
    @NotBlank
    private String motDePasse;
    @Column
    private boolean notificationsEnabled;

    private String role;

    // rajouté par Khalil
    private TypeMobilite typeMobilite;

    private int nbIncidentsReportes ;

    private int nbAlertesVerifies ;

    private int nbAlertesRencontres ;
    @OneToMany(mappedBy = "userEntity")
    private Set<ItineraireEntity> itineraireEntities;

    @OneToMany(mappedBy = "userEntity")
    private Set<SignalementEntity> signalementEntities;
}

package m1_miage.tlse.ioe.G5.moovly.models;

import jakarta.persistence.*;
import lombok.*;
import m1_miage.tlse.ioe.G5.moovly.enums.EtatSignalement;
import m1_miage.tlse.ioe.G5.moovly.enums.TypeProbleme;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignalementEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private Double longitude ;

    @Column(nullable = false)
    private Double latitude ;

    @Enumerated(EnumType.STRING)
    private EtatSignalement etatSignalement;

    @Enumerated(EnumType.STRING)
    private TypeProbleme typeProbleme;

    private String description;
    private String photoUrl;
    private LocalDateTime dateCreation;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private LieuEntity lieuEntity;

    @ManyToOne
    private ItineraireEntity itineraireEntity ;
}

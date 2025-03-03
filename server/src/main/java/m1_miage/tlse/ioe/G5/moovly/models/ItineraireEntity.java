package m1_miage.tlse.ioe.G5.moovly.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItineraireEntity {
    @Id
    private Long idItineraire;

    @Column(nullable = false)
    private Double startLongitude;

    @Column(nullable = false)
    private Double startLatitude;

    @Column(nullable = false)
    private Double endLongitude;

    @Column(nullable = false)
    private Double endLatitude;

    private double distance;
    private int estimatedTime;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private LieuEntity lieuEntity;
}

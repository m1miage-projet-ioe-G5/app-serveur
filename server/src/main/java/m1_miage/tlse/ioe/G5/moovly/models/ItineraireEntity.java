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
    @Column(columnDefinition = "geometry",nullable = false)
    private Point startPoint;

    @Column(columnDefinition = "geometry", nullable = false)
    private Point endPoint;

    private double distance;
    private int estimatedTime;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private LieuEntity lieuEntity;
}

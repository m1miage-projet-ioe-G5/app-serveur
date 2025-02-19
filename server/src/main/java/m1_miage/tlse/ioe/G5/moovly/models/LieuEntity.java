package m1_miage.tlse.ioe.G5.moovly.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.geo.Point;

import java.time.LocalDate;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LieuEntity {
    @Id
    private String numero;
    private String nom;
    @Column(columnDefinition = "geometry",nullable = false)
    private Point geoPosition;
    private boolean toiletteAdapte;

    private boolean rampeAccessible;

    private boolean besoinAccenseur;
    private LocalDate dateMjour;
    private Double noteAccessibilite;
}

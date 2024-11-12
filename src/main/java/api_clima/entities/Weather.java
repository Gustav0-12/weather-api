package api_clima.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "weather")
public class Weather implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Main main;

    @Embedded
    private Sys sys;

    @Data
    @Embeddable
    public class Main implements Serializable {
        private Double temp;
        private Double feels_like;
        private Double humidity;
    }

    @Data
    @Embeddable
    public class Sys implements Serializable{
        private String country;
    }
}

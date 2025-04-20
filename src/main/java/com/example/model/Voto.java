package com.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Entidade de Voto")
@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eleitorId;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pautaId;

    private String voto; //Dever ser SIM ou NÃO
}

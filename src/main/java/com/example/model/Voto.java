package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo 'eleitorId' é obrigatório e não pode ser nulo ou vazio")
    @Positive(message = "O valor deve ser um número positivo")
    private Long eleitorId;

    @NotNull(message = "O campo 'pautaId' é obrigatório e não pode ser nulo ou vazio")
    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pautaId;

    @NotBlank(message = "O campo 'voto' é obrigatório e não pode ser nulo ou vazio")
    @Pattern(regexp = "SIM|NÃO", message = "O campo 'voto' deve ser 'SIM' ou 'NÃO'")
    private String voto; //Dever ser SIM ou NÃO
}

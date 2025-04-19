package com.example.dto;

import com.example.model.Pauta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VotoDTO {
    @NotNull(message = "O campo 'eleitorId' é obrigatório e não pode ser nulo ou vazio")
    @Positive(message = "O valor deve ser um número positivo")
    private Long eleitorId;

    @NotNull(message = "O campo 'pautaId' é obrigatório e não pode ser nulo ou vazio")
    private Long pautaId;

    @NotBlank(message = "O campo 'voto' é obrigatório e não pode ser nulo ou vazio")
    @Pattern(regexp = "SIM|NÃO", message = "O campo 'voto' deve ser 'SIM' ou 'NÃO'")
    private String voto; //Dever ser SIM ou NÃO
}

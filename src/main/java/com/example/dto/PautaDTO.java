package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PautaDTO {

    @NotBlank(message = "O campo 'descricao' é obrigatório e não pode ser nulo ou vazio")
    private String descricao;

    private Long duracaoEmMinutos;

}

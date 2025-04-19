package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Long duracaoEmMinutos;
    private LocalDateTime dataCriacao;

    //validando se sessao está aberta para votacao, se o momnto atual é anterior ao horário de fim da sessao retorna true
    public boolean isSessaoAberta() {
        LocalDateTime fimDaSessao = this.dataCriacao.plusMinutes(this.duracaoEmMinutos);
        return LocalDateTime.now().isBefore(fimDaSessao);
    }
}
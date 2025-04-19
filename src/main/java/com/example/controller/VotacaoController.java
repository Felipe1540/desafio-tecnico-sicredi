package com.example.controller;

import com.example.dto.PautaDTO;
import com.example.dto.VotoDTO;
import com.example.model.Pauta;
import com.example.service.VotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VotacaoController {

    private final VotacaoService votacaoService;

    @PostMapping("/pauta")
    public Pauta cadastrarPauta(@RequestBody @Valid PautaDTO pautaDto) {
        return votacaoService.cadastrarPauta(pautaDto);
    }

    @PostMapping("/votar")
    public void cadastrarVoto(@RequestBody @Valid VotoDTO votoDto) {
        votacaoService.cadastrarVoto(votoDto);
    }

    @GetMapping("/contagem")
    public String contarVotos(@RequestParam Long pautaId) {
        return votacaoService.contagemDeVotos(pautaId);
    }
}

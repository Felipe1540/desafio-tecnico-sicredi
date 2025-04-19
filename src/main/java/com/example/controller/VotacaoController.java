package com.example.controller;

import com.example.dto.PautaDTO;
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
    public void cadastrarVoto(@RequestBody @Valid Map<String, Object> request) {
        Long eleitorId = Long.valueOf(request.get("eleitorId").toString());
        Long pautaId = Long.valueOf(request.get("pautaId").toString());
        String voto = request.get("voto").toString();

        votacaoService.cadastrarVoto(eleitorId, pautaId, voto);
    }

    @GetMapping("/contagem")
    public String contarVotos(@RequestParam Long pautaId) {
        return votacaoService.contagemDeVotos(pautaId);
    }
}

package com.example.controller;

import com.example.model.Pauta;
import com.example.model.Voto;
import com.example.service.VotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VotacaoController {

    private final VotacaoService votacaoService;

    @PostMapping("/pauta")
    public Pauta cadastrarPauta(@RequestBody Map<String, String> request) {
        return votacaoService.cadastrarPauta(request.get("descricao"));
    }

    @PostMapping("/voto")
    public void cadastrarVoto(@RequestBody Map<String, Object> request) {
        Long eleitorId = Long.valueOf(request.get("eleitorId").toString());
        Long pautaId = Long.valueOf(request.get("pautaId").toString());
        String voto = request.get("voto").toString();

        votacaoService.cadastrarVoto(eleitorId, pautaId, voto);
    }
}

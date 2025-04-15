package com.example.controller;

import com.example.model.Pauta;
import com.example.service.VotacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class VotacaoController {

    private VotacaoService votacaoService;

    @PostMapping
    public Pauta cadastrarPauta(@RequestBody Map<String, String> request) {
        return votacaoService.cadastrarPauta(request.get("descricao"));
    }
}

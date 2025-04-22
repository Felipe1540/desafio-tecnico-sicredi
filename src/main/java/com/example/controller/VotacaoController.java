package com.example.controller;

import com.example.dto.PautaDTO;
import com.example.dto.VotoDTO;
import com.example.model.Pauta;
import com.example.model.Voto;
import com.example.service.VotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Votação", description = "Endpoints para votação em pautas.")
public class VotacaoController {

    private final VotacaoService votacaoService;

    @Operation(summary = "Cadastrar uma pauta.")
    @PostMapping("/pauta")
    public Pauta cadastrarPauta(@RequestBody @Valid PautaDTO pautaDto) {
        return votacaoService.cadastrarPauta(pautaDto);
    }

    @Operation(summary = "Cadastrar um voto")
    @PostMapping("/votar")
    public void cadastrarVoto(@RequestBody @Valid VotoDTO votoDto) {
        votacaoService.cadastrarVoto(votoDto);
    }

    @Operation(summary = "Buscar a contagem de votos de determinada pauta. api/contagem?pautaId=ID")
    @GetMapping("/contagem")
    public String contarVotos(@RequestParam Long pautaId) {
        return votacaoService.contagemDeVotos(pautaId);
    }

    @Operation(summary = "Buscar todas as informações dos votos de determinada pauta. api/buscarVotos?pautaId=ID")
    @GetMapping("/buscarVotos")
    public List<Voto> buscarVotos(@RequestParam Long pautaId) {
        return votacaoService.BuscarVotosPorPauta(pautaId);
    }

    @Operation(summary = "Buscar todas as informações das pautas. api/buscarPautas .")
    @GetMapping("/buscarPautas")
    public List<Pauta> buscarVotos() {
        return votacaoService.BuscarPautas();
    }
}

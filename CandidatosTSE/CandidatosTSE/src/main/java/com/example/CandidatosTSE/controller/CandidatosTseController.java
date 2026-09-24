package com.example.CandidatosTSE.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CandidatosTSE.model.Candidato;
import com.example.CandidatosTSE.service.CandidatosTseService;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

    public CandidatosTseService getCandidatosTseService() {
        return candidatosTseService;
    }

    public CandidatosTseController(CandidatosTseService candidatosTseService){
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
        @RequestParam(required = false) String cargo,
        @RequestParam(required = false) String partido,
        @RequestParam(required = false) String texto,
        Model model) {
        
        String cargoSelecionado = (cargo == null) ? "" : cargo;
        String partidoSelecionado = (partido == null) ? "" : partido;
        String textoSelecionado = (texto == null) ? "" : texto;

        List<Candidato> candidatos = candidatosTseService.filtrar(
            cargoSelecionado, partidoSelecionado, textoSelecionado);

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("totalEncontrado", candidatos.size());
        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());
        model.addAttribute("cargoSelecionado", cargoSelecionado);
        model.addAttribute("partidoSelecionado", partidoSelecionado);
        model.addAttribute("textoSelecionado", textoSelecionado);

        return "index";
        }

}

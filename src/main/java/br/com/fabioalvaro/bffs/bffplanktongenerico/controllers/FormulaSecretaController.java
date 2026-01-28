package br.com.fabioalvaro.bffs.bffplanktongenerico.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabioalvaro.bffs.libfabaocommonscore.models.Resultado;
import br.com.fabioalvaro.bffs.log.core.FabaoLog;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/formula-secreta")
@RequiredArgsConstructor
public class FormulaSecretaController {
    private final FabaoLog log;

   

    @GetMapping
    public ResponseEntity<Resultado<Map<String, String>>> getAll() {
        return Resultado.ok(Map.of("mensagem", "ola mamae"));
    }

    @GetMapping("/um")
    public ResponseEntity<Void> getMaisUm() {
        log.info("GET: metodo um");
        log.info("Acessando a fórmula secreta e retornando 403");

        return ResponseEntity.status(403).build();
    }


        @GetMapping("/dois")
    public ResponseEntity<Void> getMetodoDois() {
        log.info("metodo dois: Acessando a fórmula secreta e retornando 404");

        return ResponseEntity.status(404).build();
    }

}

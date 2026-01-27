package br.com.fabioalvaro.bffs.bffplanktongenerico.services;

import br.com.fabioalvaro.bffs.libfabaocommonscore.models.Resultado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefasService {

    public ResponseEntity<Resultado<Void>> retornarComErros() {
        List<String> errosCriativos = List.of(
            "O capacitor de fluxo está sem plutônio.",
            "O gnomo do servidor entrou em greve por falta de café.",
            "A rebimboca da parafuseta recusou a conexão TCP/IP."
        );

        return Resultado.erro(errosCriativos, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

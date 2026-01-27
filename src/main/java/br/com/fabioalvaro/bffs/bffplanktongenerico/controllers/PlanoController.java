package br.com.fabioalvaro.bffs.bffplanktongenerico.controllers;

import br.com.fabioalvaro.bffs.bffplanktongenerico.models.Tarefa;
import br.com.fabioalvaro.bffs.bffplanktongenerico.services.TarefasService;
import br.com.fabioalvaro.bffs.libfabaocommonscore.models.Resultado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/planos")
@RequiredArgsConstructor
public class PlanoController {
    private final TarefasService tarefasService;

    @GetMapping
    public ResponseEntity<Resultado<List<Tarefa>>> getAll() {
        List<Tarefa> tarefas = List.of(
            Tarefa.builder().id(1L).descricao("Tarefa Mock 1").build(),
            Tarefa.builder().id(2L).descricao("Tarefa Mock 2").build()
        );
        return Resultado.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Tarefa.builder().id(id).descricao("Tarefa Mock Detalhe").build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Tarefa tarefa) {
        if ("GERAR_ERRO".equals(tarefa.getDescricao())) {
            return tarefasService.retornarComErros();
        }
        tarefa.setId(99L); // Mock ID generation
        return ResponseEntity.status(201).body(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        tarefa.setId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarefa> patch(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        tarefa.setId(id);
        if (tarefa.getDescricao() == null) {
            tarefa.setDescricao("Descricao Patched");
        }
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}

package br.com.fabioalvaro.bffs.bffplanktongenerico.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {
    private Long id;
    private String descricao;
}

package br.com.fabioalvaro.bffs.bffplanktongenerico.controllers;

import br.com.fabioalvaro.bffs.bffplanktongenerico.models.Tarefa;
import br.com.fabioalvaro.bffs.bffplanktongenerico.services.TarefasService;
import br.com.fabioalvaro.bffs.libfabaocommonscore.models.Resultado;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class PlanoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private TarefasService tarefasService;

    @BeforeEach
    void setUp() {
        PlanoController planoController = new PlanoController(tarefasService);
        mockMvc = MockMvcBuilders.standaloneSetup(planoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void create_DeveRetornarErro422_QuandoDescricaoForGerarErro() throws Exception {
        Tarefa tarefaErro = Tarefa.builder().descricao("GERAR_ERRO").build();
        List<String> erros = List.of("Erro 1", "Erro 2", "Erro 3");
        
        // Mockando o retorno do serviço
        ResponseEntity<Resultado<Void>> responseErro = Resultado.erro(erros, HttpStatus.UNPROCESSABLE_ENTITY);
        when(tarefasService.retornarComErros()).thenReturn(responseErro);

        mockMvc.perform(post("/v1/planos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefaErro)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.sucesso", is(false)))
                .andExpect(jsonPath("$.erros", hasSize(3)))
                .andExpect(jsonPath("$.erros", containsInAnyOrder("Erro 1", "Erro 2", "Erro 3")));
    }

    @Test
    void getAll_DeveRetornarListaDeTarefas() throws Exception {
        mockMvc.perform(get("/v1/planos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dados", hasSize(2)))
                .andExpect(jsonPath("$.dados[0].id", is(1)))
                .andExpect(jsonPath("$.dados[0].descricao", is("Tarefa Mock 1")))
                .andExpect(jsonPath("$.dados[1].id", is(2)))
                .andExpect(jsonPath("$.dados[1].descricao", is("Tarefa Mock 2")));
    }

    @Test
    void getById_DeveRetornarTarefaPorId() throws Exception {
        mockMvc.perform(get("/v1/planos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Tarefa Mock Detalhe")));
    }

    @Test
    void create_DeveCriarNovaTarefa() throws Exception {
        Tarefa novaTarefa = Tarefa.builder().descricao("Nova Tarefa").build();

        mockMvc.perform(post("/v1/planos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novaTarefa)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(99)))
                .andExpect(jsonPath("$.descricao", is("Nova Tarefa")));
    }

    @Test
    void update_DeveAtualizarTarefa() throws Exception {
        Tarefa tarefaAtualizada = Tarefa.builder().descricao("Tarefa Atualizada").build();

        mockMvc.perform(put("/v1/planos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefaAtualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Tarefa Atualizada")));
    }

    @Test
    void patch_DeveAtualizarParcialmenteTarefa() throws Exception {
        Tarefa tarefaPatch = Tarefa.builder().build(); // Descricao null

        mockMvc.perform(patch("/v1/planos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefaPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Descricao Patched")));
    }

    @Test
    void delete_DeveDeletarTarefa() throws Exception {
        mockMvc.perform(delete("/v1/planos/1"))
                .andExpect(status().isNoContent());
    }
}

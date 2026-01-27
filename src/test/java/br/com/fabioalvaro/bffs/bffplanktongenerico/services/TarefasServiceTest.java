package br.com.fabioalvaro.bffs.bffplanktongenerico.services;

import br.com.fabioalvaro.bffs.libfabaocommonscore.models.Resultado;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TarefasServiceTest {

    @Test
    void deveRetornarErrosCriativosComStatus422() {
        TarefasService service = new TarefasService();

        ResponseEntity<Resultado<Void>> response = service.retornarComErros();

        // Valida o Status Code
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());

        // Valida o corpo da resposta
        Resultado<Void> resultado = response.getBody();
        assertNotNull(resultado);
        
        // Verifica se a lista de erros não está vazia e tem 3 itens
        List<String> erros = resultado.getErros();
        assertNotNull(erros);
        assertEquals(3, erros.size());

        // Valida o conteúdo dos erros criativos
        assertTrue(erros.contains("O capacitor de fluxo está sem plutônio."));
        assertTrue(erros.contains("O gnomo do servidor entrou em greve por falta de café."));
        assertTrue(erros.contains("A rebimboca da parafuseta recusou a conexão TCP/IP."));
    }
}

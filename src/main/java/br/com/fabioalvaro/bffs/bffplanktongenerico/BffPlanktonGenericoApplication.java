package br.com.fabioalvaro.bffs.bffplanktongenerico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fabioalvaro.bffs.swagger.EnableFabaoOpenApi;

@SpringBootApplication
@EnableFabaoOpenApi(
    title = "BFF Clientes",
    description = "API responsável pela orquestração de dados dos clientes da fenda do bikini para o Frontend",
    version = "1.5.0"
)
public class BffPlanktonGenericoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffPlanktonGenericoApplication.class, args);
	}

}

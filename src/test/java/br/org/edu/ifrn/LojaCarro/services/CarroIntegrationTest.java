package br.org.edu.ifrn.LojaCarro.services;

import br.org.edu.ifrn.LojaCarro.model.Carro;
import br.org.edu.ifrn.LojaCarro.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.config.name=application-test")
@ActiveProfiles("test")
public class CarroIntegrationTest {

    @Autowired
    private CarroRepository carroRepository;

    // --- SALVAR ---
    @Test
    void salvarCarroSucesso() {
        Carro carro = new Carro();
        carro.setModelo("Gol");
        carro.setAno(2015);
        carro.setPreco(25000.0);

        Carro salvo = carroRepository.saveAndFlush(carro);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getModelo()).isEqualTo("Gol");
        assertThat(salvo.getPreco()).isEqualTo(25000.0);
    }

    // --- LISTAR ---
    @Test
    void listarTodosCarrosSucesso() {
        List<Carro> carros = carroRepository.findAll();
        assertThat(carros).isNotEmpty(); // deve ter os registros do data.sql
    }

    @Test
    void listarTodosCarrosErro() {
        carroRepository.deleteAll();
        List<Carro> carros = carroRepository.findAll();
        assertThrows(RuntimeException.class, () -> {
            if (!carros.isEmpty()) {
                throw new RuntimeException("Ainda existem carros!");
            }
        });
    }

    // --- BUSCAR POR ID ---
    @Test
    void buscarCarroPorIdSucesso() {
        Optional<Carro> encontrado = carroRepository.findById(1L); // Uno do data.sql
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getModelo()).isEqualTo("Uno");
    }

    @Test
    void buscarCarroPorIdErro() {
        assertThrows(EntityNotFoundException.class, () -> {
            Carro inexistente = carroRepository.getReferenceById(999L);
            inexistente.getModelo(); // força proxy a buscar no banco
        });
    }

    // --- ATUALIZAR ---
    @Test
    void atualizarCarroSucesso() {
        Optional<Carro> encontrado = carroRepository.findById(3L); // Fiesta do data.sql
        assertThat(encontrado).isPresent();

        Carro carro = encontrado.get();
        carro.setModelo("Fiesta Sedan");
        carro.setPreco(23000.0);
        Carro atualizado = carroRepository.saveAndFlush(carro);

        assertThat(atualizado.getModelo()).isEqualTo("Fiesta Sedan");
        assertThat(atualizado.getPreco()).isEqualTo(23000.0);
    }

    @Test
    void atualizarCarroErro() {
        assertThrows(EntityNotFoundException.class, () -> {
            Carro inexistente = carroRepository.getReferenceById(999L);
            inexistente.getModelo(); // força proxy a buscar no banco
            inexistente.setModelo("Inexistente");
            inexistente.setPreco(10000.0);
            carroRepository.saveAndFlush(inexistente);
        });
    }

    // --- DELETAR ---
    @Test
    void deletarCarroSucesso() {
        carroRepository.deleteById(2L); // Celta do data.sql
        Optional<Carro> encontrado = carroRepository.findById(2L);
        assertThat(encontrado).isEmpty();
    }

    @Test
    void deletarCarroErro() {
        assertThrows(EntityNotFoundException.class,
                () -> carroRepository.getReferenceById(999L).getModelo());
    }
}

package com.sofiasaless.desafiobackend.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofiasaless.desafiobackend.dto.UsuarioRequestDTO;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsuariosControllerTest {

    // configurações iniciais
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
        .build();
    }

    @Test
    public void deveEstarAptoASalvarUmNovoUsuario() throws Exception {
        var novoUsuario = criarUsuarioDTOValido();

        mvc.perform(
            MockMvcRequestBuilders.post("/usuarios/cadastrar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objetoParaJson(novoUsuario))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void naoDeveEstarAptoASalvarUmNovoUsuarioComCamposVazios() throws Exception {
        var novoUsuario = criarUsuarioDTOComCamposVazios();

        mvc.perform(
            MockMvcRequestBuilders.post("/usuarios/cadastrar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objetoParaJson(novoUsuario))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    // METODOS UTEIS

    private UsuarioRequestDTO criarUsuarioDTOValido() {
        return UsuarioRequestDTO.builder()
            .nome("CATELYN TESTER")
            .email("CATELYN@test.com")
            .documentacao("12345678900")
            .senha("test123")
            .saldo(1000.0)
            .tipoDoUsuario(TipoUsuario.NORMAL)
        .build();
    }

    private UsuarioRequestDTO criarUsuarioDTOComCamposVazios() {
        return UsuarioRequestDTO.builder()
            .nome("")
            .email("")
            .documentacao("")
            .senha("")
            .saldo(0.0)
            .tipoDoUsuario(null)
        .build();
    }


    private static String objetoParaJson(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

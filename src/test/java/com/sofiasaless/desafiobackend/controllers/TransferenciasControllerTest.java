package com.sofiasaless.desafiobackend.controllers;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofiasaless.desafiobackend.dto.TransferenciaRequestDTO;
import com.sofiasaless.desafiobackend.useCase.TransferirValorUseCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransferenciasControllerTest {

    // configurações iniciais
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private RestTemplate rt;
    
    @MockBean
    private TransferirValorUseCase transferirValorUseCase;
    
    @Value("${authentication.transf.url}")
    private String urlAuth;
    
    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
        .build();
    }

    @Test
    public void deveEstarAptoAEfetuarUmaTransferencia() throws Exception {
        
        var novaTransferencia = criarTransferenciaValida();
        when(rt.getForEntity(urlAuth, Map.class)).thenReturn(ResponseEntity.ok(new HashMap<>()));

        mvc.perform(
            MockMvcRequestBuilders.post("/transferencia/pagar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objetoParaJson(novaTransferencia))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void naoDeveEstarAptoAEfetuarUmaTransferenciaComValorInvalido() throws Exception {
        
        var novaTransferencia = criarTransferenciaInvalida();
        when(rt.getForEntity(urlAuth, Map.class)).thenReturn(ResponseEntity.ok(new HashMap<>()));

        mvc.perform(
            MockMvcRequestBuilders.post("/transferencia/pagar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objetoParaJson(novaTransferencia))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    // METODOS UTEIS

    private static String objetoParaJson(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TransferenciaRequestDTO criarTransferenciaValida() {
        return TransferenciaRequestDTO.builder()
            .valor(100)
            .pagadorId(1l)
            .beneficiarioId(2l)
        .build();
    }

    private TransferenciaRequestDTO criarTransferenciaInvalida() {
        return TransferenciaRequestDTO.builder()
            .valor(-2)
            .pagadorId(1l)
            .beneficiarioId(2l)
        .build();
    }


}
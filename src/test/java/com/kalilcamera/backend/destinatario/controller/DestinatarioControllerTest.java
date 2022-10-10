package com.kalilcamera.backend.destinatario.controller;

import Stub.DestinatarioTOStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.service.DestinatarioService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DestinatarioController.class)
@Disabled
class DestinatarioControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DestinatarioService destinatarioService;

    @Test
    void cadastrar_success() throws Exception {
        Destinatario destinatario = DestinatarioTOStub.valid();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/destinatario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(destinatario));

        mockMvc.perform(mockRequest).andExpect(status().isCreated());
    }

    @Test
    public void listAll_success() throws Exception {
        Destinatario destinatario = DestinatarioTOStub.valid();

        List<Destinatario> destinatarioList = new ArrayList<>(Arrays.asList(destinatario));

        Mockito.when(destinatarioService.listAll()).thenReturn(destinatarioList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/destinatario/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}



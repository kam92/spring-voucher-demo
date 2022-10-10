package com.kalilcamera.backend.ofertaespecial.controller;

import Stub.OfertaEspecialTOStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalilcamera.backend.destinatario.controller.DestinatarioController;
import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import com.kalilcamera.backend.ofertaespecial.service.OfertaEspecialService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DestinatarioController.class)
@Disabled
class OfertaEspecialControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OfertaEspecialService ofertaEspecialService;

    @Test
    void cadastrar_success() throws Exception {
        OfertaEspecial ofertaEspecial = OfertaEspecialTOStub.valid();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ofertaespecial/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ofertaEspecial));

        mockMvc.perform(mockRequest).andExpect(status().isCreated());
    }

    @Test
    public void listAll_success() throws Exception {
        OfertaEspecial ofertaEspecial = OfertaEspecialTOStub.valid();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ofertaespecial/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

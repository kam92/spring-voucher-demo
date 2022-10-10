package com.kalilcamera.backend.voucher.controller;

import Stub.VoucherTOStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalilcamera.backend.ofertaespecial.service.OfertaEspecialService;
import com.kalilcamera.backend.voucher.entity.Voucher;
import com.kalilcamera.backend.voucher.service.VoucherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoucherController.class)
class VoucherControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    VoucherService voucherService;

    @MockBean
    OfertaEspecialService ofertaEspecialService;

    @Test
    void gerarVouchers_400() throws Exception {
        Voucher vo = VoucherTOStub.valid();
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/voucher/gerar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(vo));

        mockMvc.perform(mockRequest).andExpect(status().is4xxClientError());
    }


}

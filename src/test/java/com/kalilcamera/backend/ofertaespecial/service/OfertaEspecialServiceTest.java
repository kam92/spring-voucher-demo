package com.kalilcamera.backend.ofertaespecial.service;

import Stub.OfertaEspecialTOStub;
import com.kalilcamera.backend.destinatario.repository.DestinatarioRepository;
import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OfertaEspecialServiceTest {

    @InjectMocks
    private OfertaEspecialService service;

    @Mock
    private DestinatarioRepository repository;

    @Test
    void givenValidBodyThanReturnSuccess() {

        OfertaEspecial ofertaEspecial = OfertaEspecialTOStub.valid();

        assertNotNull(ofertaEspecial.getNome());
        assertNotNull(ofertaEspecial.getDescontoPercentual());
    }

}
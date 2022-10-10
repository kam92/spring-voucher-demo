package com.kalilcamera.backend.destinatario.service;

import Stub.DestinatarioTOStub;
import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.repository.DestinatarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DestinatarioServiceTest {

    @InjectMocks
    private DestinatarioService service;

    @Mock
    private DestinatarioRepository repository;

    @Test
    void givenValidBodyThanReturnSuccess() {

        Destinatario destinatario = DestinatarioTOStub.valid();

        assertNotNull(destinatario.getEmail());
        assertNotNull(destinatario.getNome());
    }

}

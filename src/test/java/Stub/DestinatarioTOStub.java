package Stub;

import com.kalilcamera.backend.destinatario.entity.Destinatario;

public class DestinatarioTOStub {
    public static Destinatario valid() {
        return Destinatario.builder()
                .id("12345678")
                .nome("nome")
                .email("email@teste.com")
                .build();
    }
}

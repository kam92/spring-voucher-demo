package Stub;

import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;

public class OfertaEspecialTOStub {
    public static OfertaEspecial valid() {
        return OfertaEspecial.builder()
                .id("12345678")
                .nome("nome")
                .descontoPercentual(10F)
                .build();
    }
}

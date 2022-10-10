package Stub;

import com.kalilcamera.backend.voucher.entity.Voucher;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherTOStub {
    public static Voucher valid() {
        return Voucher.builder()
                .id("12345678")
                .ofertaEspecial(OfertaEspecialTOStub.valid())
                .codigo("12345")
                .dataExpiracao(LocalDateTime.MAX)
                .destinatario(DestinatarioTOStub.valid())
                .build();
    }
}

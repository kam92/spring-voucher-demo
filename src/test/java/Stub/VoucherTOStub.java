package Stub;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import com.kalilcamera.backend.voucher.entity.Voucher;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherTOStub {
    public static Voucher valid() {
        return Voucher.builder()
                .id("12345678")
                .ofertaEspecial(new OfertaEspecial())
                .codigo("12345")
                .dataExpiracao(LocalDateTime.MAX)
                .destinatario(new Destinatario())
                .build();
    }
}

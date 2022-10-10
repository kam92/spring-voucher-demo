package com.kalilcamera.backend.voucher.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ValidarVoucherTO {
    public Float desconto;
    public LocalDateTime dataExpiracao;
}

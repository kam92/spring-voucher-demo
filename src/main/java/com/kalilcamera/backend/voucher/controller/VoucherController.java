package com.kalilcamera.backend.voucher.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kalilcamera.backend.voucher.service.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Voucher")
@RequestMapping("/voucher")
@SecurityRequirement(name = "secureapi")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro ")
})
public class VoucherController {
    @Autowired
    VoucherService voucherService;


    @PostMapping("/gerar")
    @Operation(summary = "Gerar vouchers.", description = "Gera um voucher por destinatário, baseado na Oferta Especial e data definida.")
    public ResponseEntity<?> gerarVouchers(String ofertaEspecialNome, @RequestBody @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataExpiracao) {
        return voucherService.gerarVouchers(ofertaEspecialNome, dataExpiracao);
    }

    @PostMapping("/validar")
    @Operation(summary = "Validar voucher.", description = "Exibe a data de validade e percentual de desconto do Voucher.")
    public ResponseEntity<?> validarVoucher(String codigo, String email) {
        return voucherService.validarVoucher(codigo, email);
    }

    @PostMapping("/utilizar")
    @Operation(summary = "Utilizar o voucher.", description = "Marca o voucher como utilizado.")
    public ResponseEntity<?> utilizarVoucher(String codigo, String email) {
        return voucherService.utilizarVoucher(codigo, email);
    }

}

package com.kalilcamera.backend.voucher.entity;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "voucher")
public class Voucher implements Serializable {

    @Id
    @Hidden
    private String id;

    @NotNull
    @Field
    @Schema(example = "2022-10-31 00:00:00", description = "data de validade do voucher")
    private LocalDateTime dataExpiracao;

    @NotNull
    @Field
    @Schema(description = "Oferta Especial")
    private OfertaEspecial ofertaEspecial;

    @NotNull
    @Field
    @Schema(description = "Destinatário")
    private Destinatario destinatario;

    @Field
    @Schema(example = "2022-10-31 00:00:00", description = "data de validade do voucher")
    private LocalDateTime dataUtilizacao;

    @Field
    @NotBlank
    private String codigo;
}
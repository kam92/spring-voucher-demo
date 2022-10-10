package com.kalilcamera.backend.ofertaespecial.entity;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "ofertaespecial")
public class OfertaEspecial implements Serializable {

    @Id
    @Hidden
    private String id;

    @NotBlank
    @Field
    @Schema(example = "NATAL-2022", description = "Nome da Oferta")
    private String nome;

    @NotNull
    @Field
    @Schema(example = "49.25", description = "Percentual de Desconto")
    private Float descontoPercentual;
}
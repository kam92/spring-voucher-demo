package com.kalilcamera.backend.destinatario.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "destinatario")
public class Destinatario implements Serializable {

    @Id
    @Hidden
    private String id;

    @NotBlank
    @Email
    @Field
    @Schema(example = "valid@email.com", description = "email")
    private String email;

    @NotBlank
    @Field
    @Schema(description = "Nome do Destinatário")
    private String nome;

}
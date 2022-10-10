package com.kalilcamera.backend.destinatario.controller;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.service.DestinatarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Destinatário")
@RequestMapping("/destinatario")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Verifique os campos e tente novamente")
})
public class DestinatarioController {
    @Autowired
    DestinatarioService destinatarioService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar destinatário.", description = "Insere um novo destinatário no sistema.")
    @ApiResponse(responseCode = "201", description = "Cadastrado")
    public ResponseEntity<?> createDestinatario(@RequestBody @Valid Destinatario destinatario) {
        return destinatarioService.save(destinatario);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar destinatários.", description = "Lista destinatários cadastrados.")
    public ResponseEntity<?> listAllUserAccount() {
        return destinatarioService.getAll();
    }
}

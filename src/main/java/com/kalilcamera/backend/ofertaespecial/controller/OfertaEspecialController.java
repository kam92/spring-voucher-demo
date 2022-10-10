package com.kalilcamera.backend.ofertaespecial.controller;

import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import com.kalilcamera.backend.ofertaespecial.service.OfertaEspecialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Oferta Especial")
@RequestMapping("/ofertaespecial")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro no Servidor")
})
public class OfertaEspecialController {
    @Autowired
    OfertaEspecialService ofertaEspecialService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar oferta especial.", description = "Insere uma nova oferta especial no sistema")
    @ApiResponse(responseCode = "201", description = "Cadastrado")
    public ResponseEntity<?> createUserAccount(@RequestBody @Valid OfertaEspecial ofertaEspecial) {
        return ofertaEspecialService.save(ofertaEspecial);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar ofertas especiais.", description = "Lista ofertas especiais cadastradas.")
    public ResponseEntity<?> listAllUserAccount() {
        return ofertaEspecialService.getAll();
    }


}

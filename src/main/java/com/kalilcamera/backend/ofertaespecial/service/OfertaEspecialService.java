package com.kalilcamera.backend.ofertaespecial.service;

import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import com.kalilcamera.backend.ofertaespecial.repository.OfertaEspecialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OfertaEspecialService {

    @Autowired
    private OfertaEspecialRepository ofertaEspecialRepository;


    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(ofertaEspecialRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao listar ofertas especiais: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> save(OfertaEspecial ofertaEspecialInput) {
        try {
            ofertaEspecialRepository.save(new OfertaEspecial().toBuilder()
                    .nome(ofertaEspecialInput.getNome())
                    .descontoPercentual(ofertaEspecialInput.getDescontoPercentual())
                    .build());
            return new ResponseEntity<>(ofertaEspecialInput, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("oferta especial já cadastrada.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Erro ao cadastrar destinatário {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<OfertaEspecial> findByNome(String name) {
        return Optional.ofNullable(ofertaEspecialRepository.findByNome(name));
    }


}

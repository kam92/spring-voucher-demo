package com.kalilcamera.backend.destinatario.service;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.repository.DestinatarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DestinatarioService {

    @Autowired
    private DestinatarioRepository destinatarioRepository;


    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(destinatarioRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao listar destinatarios: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> save(Destinatario destinatarioInput) {
        try {
            destinatarioRepository.save(new Destinatario().toBuilder()
                    .nome(destinatarioInput.getNome())
                    .email(destinatarioInput.getEmail()).build());
            return new ResponseEntity<>(destinatarioInput, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("destinatário já cadastrado.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("Erro ao cadastrar destinatário {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Destinatario> listAll() {
        return destinatarioRepository.findAll();
    }


}

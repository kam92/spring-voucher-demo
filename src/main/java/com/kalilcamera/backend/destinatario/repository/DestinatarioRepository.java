package com.kalilcamera.backend.destinatario.repository;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DestinatarioRepository extends MongoRepository<Destinatario, Long> {

}

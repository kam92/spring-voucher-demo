package com.kalilcamera.backend.ofertaespecial.repository;

import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfertaEspecialRepository extends MongoRepository<OfertaEspecial, Long> {
    OfertaEspecial findByNome(String name);
}

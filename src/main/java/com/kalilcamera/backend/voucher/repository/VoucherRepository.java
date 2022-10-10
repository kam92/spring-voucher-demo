package com.kalilcamera.backend.voucher.repository;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.voucher.entity.Voucher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VoucherRepository extends MongoRepository<Voucher, Long> {

    Optional<Voucher> findByCodigo(String codigo);

    Voucher findByDestinatario(Destinatario destinatario);
}

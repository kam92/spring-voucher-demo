package com.kalilcamera.backend.voucher.service;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.service.DestinatarioService;
import com.kalilcamera.backend.ofertaespecial.entity.OfertaEspecial;
import com.kalilcamera.backend.ofertaespecial.service.OfertaEspecialService;
import com.kalilcamera.backend.voucher.entity.Voucher;
import com.kalilcamera.backend.voucher.repository.VoucherRepository;
import com.kalilcamera.backend.voucher.to.ValidarVoucherTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class VoucherService {

    private final String DADOS_INVALIDOS = "Dados inválidos.";
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private OfertaEspecialService ofertaEspecialService;
    @Autowired
    private DestinatarioService destinatarioService;

    public ResponseEntity<?> gerarVouchers(String ofertaEspecialNome, LocalDateTime dataExpiracao) {
        try {
            Optional<OfertaEspecial> ofertaEspecial = ofertaEspecialService.findByNome(ofertaEspecialNome);
            List<Destinatario> destinatarioList = destinatarioService.listAll();
            ArrayList<Voucher> voucherList = new ArrayList<>();

            for (Destinatario destinatario : destinatarioList) {

                voucherList.add(new Voucher().toBuilder().dataExpiracao(dataExpiracao)
                        .codigo(gerarCodigo())
                        .destinatario(destinatario)
                        .ofertaEspecial(ofertaEspecial.get())
                        .build());
            }

            return new ResponseEntity<>(voucherRepository.insert(voucherList), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Erro ao gerar vouchers: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String gerarCodigo() {
        char[] chars = "ABCDEFGHIJKLMNOPQQRSTUVXWYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        int i = 0;
        while (i < 8) { //tamanho do v oucher
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
            i++;
        }
        return sb.toString();
    }

    public ResponseEntity<?> validarVoucher(String codigo, String email) {
        try {
            Voucher voucher = checarVoucher(codigo, email);

            return new ResponseEntity<>(new ValidarVoucherTO().toBuilder()
                    .dataExpiracao(voucher.getDataExpiracao())
                    .desconto(voucher.getOfertaEspecial().getDescontoPercentual())
                    .build(), HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Erro ao gerar vouchers: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Voucher checarVoucher(String codigo, String email) {
        Voucher voucher = voucherRepository.findByCodigo(codigo).get();

        if (!voucher.getDestinatario().getEmail()
                .equals(email)) {
            throw new NoSuchElementException();
        }

        if (voucher.getDataUtilizacao() != null) {
            throw new NoSuchElementException();
        }

        return voucher;
    }

    public ResponseEntity<?> utilizarVoucher(String codigo, String email) {
        try {
            Voucher voucher = checarVoucher(codigo, email);
            voucher.setDataUtilizacao(LocalDateTime.now());
            voucherRepository.save(voucher);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(DADOS_INVALIDOS, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Erro ao utilizar voucher: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Voucher findByDestinatario(Destinatario destinatario) {
        return voucherRepository.findByDestinatario(destinatario);
    }

    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }


}

package com.kalilcamera.backend.destinatario.service;

import com.kalilcamera.backend.destinatario.entity.Destinatario;
import com.kalilcamera.backend.destinatario.repository.DestinatarioRepository;
import com.kalilcamera.backend.destinatario.to.VoucherAtivoTO;
import com.kalilcamera.backend.voucher.entity.Voucher;
import com.kalilcamera.backend.voucher.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class DestinatarioService {

    @Autowired
    private DestinatarioRepository destinatarioRepository;

    @Autowired
    private VoucherService voucherService;

    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(destinatarioRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao listar ofertas especiais: {}", e.getMessage());
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

    public ResponseEntity<?> listAllActiveVoucher(String email) {
        try {
            Destinatario destinatario = destinatarioRepository.findyByEmail(email).get();
            List<Voucher> voucherList = voucherService.findAll();
            List<VoucherAtivoTO> voucherAtivoTO = null;
            for (int i = 0; i < voucherList.size(); i++) {
                VoucherAtivoTO voucherAtivoTOLista = new VoucherAtivoTO();
                if (voucherList.get(i).getDestinatario().equals(destinatario)) {
                    voucherAtivoTOLista.setCodigoVoucher(voucherList.get(i).getCodigo());
                    voucherAtivoTOLista.setOfertaEspecial(voucherList.get(i).getOfertaEspecial().getNome());
                    voucherAtivoTO.add(voucherAtivoTOLista);
                }
            }

            return new ResponseEntity<>(voucherAtivoTO, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Dados inválidos", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Erro ao utilizar voucher: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

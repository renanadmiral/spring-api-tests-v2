package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;
import com.aula04.banco.banco.service.OperacoesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    OperacoesService operacoesService = new OperacoesService();

    @PatchMapping("/deposita")
    public ResponseEntity deposita(
            @RequestHeader("id") UUID id,
            @RequestBody RequestDeposito requestDeposito
    ) throws Exception {
        operacoesService.deposita(id, requestDeposito);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/saca")
    public ResponseEntity saca(
            @RequestHeader("id") UUID id, 
            @RequestBody RequestSaque requestSaque
    ) throws Exception {
        
        operacoesService.saca(id, requestSaque);
        return ResponseEntity.ok().build();
    }

}

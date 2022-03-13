package com.aula04.banco.banco.service;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;

import java.util.UUID;

public class OperacoesService {

     public RequestDeposito deposita(UUID id, RequestDeposito requestDeposito) throws Exception {
          BancoAula04Application.bancoCliente.deposita(id, requestDeposito);
          return requestDeposito;
     }

     public RequestSaque saca(UUID id, RequestSaque requestSaque) throws Exception {
          BancoAula04Application.bancoCliente.saca(id, requestSaque);
          return requestSaque;
     }
}

package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;
import com.aula04.banco.banco.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class OperacoesServiceTest {

    OperacoesService operacoesService = new OperacoesService();
    private ClienteService clienteService = new ClienteService();

    @Test
    void naoDeveDepositarEmContaNaoEncontrada() {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(Exception.class, () -> operacoesService.deposita(id, new RequestDeposito(1200.00, UUID.randomUUID())));
    }

    @Test
    void deveDepositarEmContaValida() throws Exception {
        Cliente cliente = clienteService.cadastraCliente(new RequestCliente(
                "Renan",
                "renan@renan.com",
                "12345678900",
                "12345",
                33));


        UUID clienteId = cliente.getId();
        UUID contaId = cliente.getContas().get(0).getId();

        double saldoAnterior = cliente.getContas().get(0).getSaldo();

        operacoesService.deposita(clienteId, new RequestDeposito(1200.00, contaId));

        Assertions.assertNotEquals(saldoAnterior, cliente.getContas().get(0).getSaldo(), 0.001);
    }


    @Test
    void naoDeveSacarDeContaNaoEncontrada() {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(Exception.class, () -> operacoesService.saca(id, new RequestSaque(1200.00, UUID.randomUUID())));
    }

    @Test
    void deveSacarDeContaValida() throws Exception {
        Cliente cliente = clienteService.cadastraCliente(new RequestCliente(
                "Renan",
                "renan@renan.com",
                "12345678900",
                "123456",
                33));


        UUID clienteId = cliente.getId();
        UUID contaId = cliente.getContas().get(0).getId();

        operacoesService.deposita(clienteId, new RequestDeposito(1200.00, contaId));
        double saldoAnterior = cliente.getContas().get(0).getSaldo();

        operacoesService.saca(clienteId, new RequestSaque(500.00, contaId));

        Assertions.assertNotEquals(saldoAnterior, cliente.getContas().get(0).getSaldo(), 0.001);
    }
}

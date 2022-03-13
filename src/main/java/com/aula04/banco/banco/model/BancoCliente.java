package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();

    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> buscaClientes(){
        return BancoCliente.clientes;
    }

    public Cliente detalhesCliente(UUID id) throws Exception {
           Optional<Cliente> resultCliente =
                   BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id)).findAny();
           if(resultCliente.isPresent()){
               return resultCliente.get();
           } else {
               throw new Exception("Usuario nao encontrado");
           }
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    cliente.setNome(requestCliente.getNome());
                    cliente.setEmail(requestCliente.getEmail());
                    cliente.setSenha(requestCliente.getSenha());
                });
        return detalhesCliente(id);
    }
    public void deletaCliente(UUID id) throws Exception {
        Cliente cliente = detalhesCliente(id);
        BancoCliente.clientes.remove(cliente);
    }
    public void deposita(UUID id, RequestDeposito requestDeposito) throws Exception {
        Cliente c = null;
        c = (Cliente) BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id)).toArray()[0];
        if (c == null) {
            throw new Exception("Cliente não encontrado");
        }
        Optional<Conta> resultConta = c.getContas().stream().filter(conta -> Objects.equals(conta.getId(),requestDeposito.getConta())).findAny();
        if(resultConta.isPresent()) {
            Double novoSaldo = resultConta.get().getSaldo() + requestDeposito.getValor();
            resultConta.get().setSaldo(novoSaldo);
        } else {
            try {
                throw new Exception("Conta não encontrada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saca(UUID id, RequestSaque requestSaque) throws Exception {
        Cliente c = detalhesCliente(id);

        if (c == null) {
            throw new Exception("Cliente não encontrado");
        }
        Optional<Conta> resultConta = c.getContas().stream().filter(conta -> Objects.equals(conta.getId(),requestSaque.getConta())).findAny();
        if(resultConta.isPresent()) {
            Double novoSaldo = resultConta.get().getSaldo() - requestSaque.getValor();
            resultConta.get().setSaldo(novoSaldo);
        } else {
            try {
                throw new Exception("Conta não encontrada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

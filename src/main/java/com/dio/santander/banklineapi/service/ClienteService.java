package com.dio.santander.banklineapi.service;

import com.dio.santander.banklineapi.dto.NovoCliente;
import com.dio.santander.banklineapi.model.Cliente;
import com.dio.santander.banklineapi.model.Conta;
import com.dio.santander.banklineapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    public void save(NovoCliente novoCliente) {
        Cliente cliente = new Cliente();
        cliente.setCpf(novoCliente.getCpf());
        cliente.setNome(novoCliente.getNome());

        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setNumero(new Date().getTime());

        cliente.setConta(conta);
        repository.save(cliente);

    }
}

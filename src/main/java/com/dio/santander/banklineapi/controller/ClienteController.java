package com.dio.santander.banklineapi.controller;

import com.dio.santander.banklineapi.dto.NovoCliente;
import com.dio.santander.banklineapi.model.Cliente;
import com.dio.santander.banklineapi.repository.ClienteRepository;
import com.dio.santander.banklineapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public void save(@RequestBody NovoCliente cliente) {
        service.save(cliente);
    }
}

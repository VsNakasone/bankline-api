package com.dio.santander.banklineapi.service;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Cliente;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.model.MovimentacaoTipo;
import com.dio.santander.banklineapi.repository.ClienteRepository;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void save(NovaMovimentacao novaMovimentacao) {
        Movimentacao movimentacao = new Movimentacao();

        Double valor = novaMovimentacao.getValor();
        if(novaMovimentacao.getTipo() == MovimentacaoTipo.SAQUE)
            valor = valor* -1;

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacao.getDescricao());
        movimentacao.setIdConta(novaMovimentacao.getIdConta());
        movimentacao.setTipo(novaMovimentacao.getTipo());
        movimentacao.setValor(novaMovimentacao.getValor());

        Cliente cliente = clienteRepository.findById(novaMovimentacao.getIdConta()).orElse(null);
        if(cliente != null) {
            cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
            clienteRepository.save(cliente);
        }
        repository.save(movimentacao);
    }
}

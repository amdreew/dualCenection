package com.mo.dualcenection.controller;

import com.mo.dualcenection.respository.entity.postgres.ClienteEntity;
import com.mo.dualcenection.respository.jpa.postgres.ClienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "clientes")
public class ClienteController {
    private final ClienteJpaRepository clienteJpaRepository;


    @Autowired
    public ClienteController(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @GetMapping
    public List<ClienteEntity> getFacturas() {
        return this.clienteJpaRepository.findAll();
    }
}

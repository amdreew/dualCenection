package com.mo.dualcenection.controller;

import com.mo.dualcenection.respository.entity.mysql.FacturaEntity;
import com.mo.dualcenection.respository.jpa.mysql.FacturaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "facturas")
public class FacturaController {

    private final FacturaJpaRepository facturaJpaRepository;

    @Autowired
    public FacturaController(FacturaJpaRepository facturaJpaRepository) {
        this.facturaJpaRepository = facturaJpaRepository;
    }


    @GetMapping
    public List<FacturaEntity> getFacturas() {
        return this.facturaJpaRepository.findAll();
    }
}

package com.mo.dualcenection.respository.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaEntity {
    @Id
    private Long id;
    private String estado;
    private String cliente;
    private BigDecimal valor;
}

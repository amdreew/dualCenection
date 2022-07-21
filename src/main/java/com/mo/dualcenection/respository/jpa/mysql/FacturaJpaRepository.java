package com.mo.dualcenection.respository.jpa.mysql;

import com.mo.dualcenection.respository.entity.mysql.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaJpaRepository extends JpaRepository<FacturaEntity, Long> {
}

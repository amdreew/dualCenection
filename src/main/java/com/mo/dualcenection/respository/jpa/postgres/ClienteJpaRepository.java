package com.mo.dualcenection.respository.jpa.postgres;

import com.mo.dualcenection.respository.entity.postgres.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
}

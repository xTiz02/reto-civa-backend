package org.prd.civaback.persistence.repository;

import org.prd.civaback.persistence.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusEntity,Long> {

}
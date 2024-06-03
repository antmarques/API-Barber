package com.api.barber.model.repositories;

import com.api.barber.model.entities.SchedulingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<SchedulingEntity, Long> {
}

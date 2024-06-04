package com.api.barber.model.repositories;

import com.api.barber.model.entities.ItemSchedulingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSchedulingRepository extends JpaRepository<ItemSchedulingEntity, Long> {
}

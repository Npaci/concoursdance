package com.sampac.concoursdance.dataaccess.repositories;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcoursRepository extends JpaRepository<Concours, Long> {
}

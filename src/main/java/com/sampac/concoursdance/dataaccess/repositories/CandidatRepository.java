package com.sampac.concoursdance.dataaccess.repositories;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
}

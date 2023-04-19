package com.spring.backproject.Repository;

import com.spring.backproject.Models.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleRep extends JpaRepository<Famille,Long> {
}

package com.spring.backproject.Repository;

import com.spring.backproject.Models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRep extends JpaRepository<Validation,Integer> {

}

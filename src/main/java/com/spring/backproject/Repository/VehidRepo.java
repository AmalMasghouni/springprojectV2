package com.spring.backproject.Repository;

import com.spring.backproject.Models.Vehid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface VehidRepo extends JpaRepository<Vehid, Integer> {
}

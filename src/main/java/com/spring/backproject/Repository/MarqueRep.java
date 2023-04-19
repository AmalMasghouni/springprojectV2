package com.spring.backproject.Repository;

import com.spring.backproject.Models.Marque;
import com.spring.backproject.Models.Vehid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface MarqueRep extends JpaRepository<Vehid, Integer> {
    @Query("SELECT DISTINCT v.grpMarq FROM Vehid v")
    List<String> findAllMarque();
}

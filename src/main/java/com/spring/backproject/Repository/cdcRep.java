package com.spring.backproject.Repository;

import com.spring.backproject.Models.CDC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cdcRep extends JpaRepository<CDC,Long> {
    @Query("SELECT DISTINCT c.refCDC FROM CDC c ")
    //INNER JOIN  c.devs ORDER BY c.refCDCArdia
    List<String> findAllRefCDC();
    /*List<CDC> findByNom(String nom);*/
}

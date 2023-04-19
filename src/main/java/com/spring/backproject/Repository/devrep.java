package com.spring.backproject.Repository;

import com.spring.backproject.Models.DEV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface devrep extends JpaRepository<DEV,Long> {

   @Query("SELECT DISTINCT d.nomDev FROM DEV d")
    List<String> findAllNomDev();

}

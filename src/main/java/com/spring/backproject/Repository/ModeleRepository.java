package com.spring.backproject.Repository;

import com.spring.backproject.Models.Vehid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeleRepository extends JpaRepository<Vehid,Integer> {
    @Query("SELECT  DISTINCT CONCAT(v.nomVeh, '(', v.nomInterne,')')  from Vehid v")
    List<String> findAllNomModele();
    //@Param("nomMarque") String nomMarque
    @Query("SELECT v.nomInterne FROM Vehid v WHERE v.marque = :nommar ORDER BY v.nomInterne")
    List<String> findModelesByMarque();

}

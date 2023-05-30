package com.spring.backproject.Repository;

import com.spring.backproject.Models.Vehid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehid,Integer> {
    @Query("SELECT DISTINCT v.grpMarq FROM Vehid v")
    List<String> selectGroupeDeMarque();

    @Query("SELECT DISTINCT CONCAT (v.nomVeh,'[',v.nomInterne,']') FROM Vehid v WHERE v.grpMarq = :marque")
    List<String> selectModeleByMarque(@Param("marque") String marque);
    @Query("SELECT DISTINCT CONCAT (v.nomVeh,'[',v.nomInterne,']') FROM Vehid v ")
    List<String>selectModele();
    List<Vehid> findByNomVeh(String nomVeh);
   List<Vehid> findCodeVehByGrpMarq(String grpMarque);
   List<Vehid> findCodeVehByNomVehAndNomInterne(String nomVeh,String nomInterne);
    List<Vehid> findAllCodeVehByNomVehAndNomInterneAndGrpMarq(String nomVeh,String nomInterne,String grpMarque);
List<Vehid> findByCodeVeh(Integer codeVeh);
}

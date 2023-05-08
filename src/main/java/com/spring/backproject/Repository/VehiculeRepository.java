package com.spring.backproject.Repository;

import com.spring.backproject.Models.Marque;
import com.spring.backproject.Models.Vehid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehid,Integer> {
    @Query("SELECT DISTINCT v.grpMarq FROM Vehid v")
    List<String> selectGroupeDeMarque();
    List<Vehid> findByNomVeh(String nomVeh);

}

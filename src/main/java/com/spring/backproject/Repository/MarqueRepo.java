package com.spring.backproject.Repository;

import com.spring.backproject.Models.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MarqueRepo extends JpaRepository<Marque, Integer> {

  @Query("SELECT DISTINCT m.nommar FROM Marque m")
  List<String> selectMarque();
  Optional<Marque> findByNommar(String nommar);
List<Marque> findAllByNommar(String nommar);




}

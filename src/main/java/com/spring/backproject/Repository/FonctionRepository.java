package com.spring.backproject.Repository;

import com.spring.backproject.Models.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction,Integer> {
    List<Fonction> findByIdFonction(Integer id);
}

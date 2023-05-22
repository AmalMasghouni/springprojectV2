package com.spring.backproject.Repository;

import com.spring.backproject.Models.MAJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajRepository extends JpaRepository<MAJ,Long> {
    @Query("SELECT DISTINCT CONCAT (nomMaj,'(',typeMaj,')') From MAJ")
    List<String> findAllNomMaj();
    List<MAJ> findAllIdMajByNomMajAndTypeMaj(String nomMaj, String typeMaj);
}

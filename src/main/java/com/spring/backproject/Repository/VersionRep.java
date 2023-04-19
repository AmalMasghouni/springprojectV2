package com.spring.backproject.Repository;

import com.spring.backproject.Models.MAJ;
import com.spring.backproject.Models.VERSION_MAJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRep extends JpaRepository<MAJ,Long> {
    @Query("SELECT DISTINCT CONCAT(m.nomMaj,'(', m.typeMaj ,')')  FROM MAJ m")
    List<String> findAllVersion();
}

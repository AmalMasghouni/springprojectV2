package com.spring.backproject.Repository;

import com.spring.backproject.Models.VERSION_MAJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionMajRepository extends JpaRepository<VERSION_MAJ,Long> {
    VERSION_MAJ findByIdVer(Long idVer);
    VERSION_MAJ findByNomVer(String nomVer);
}

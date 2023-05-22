package com.spring.backproject.Repository;

import com.spring.backproject.Models.CDC;
import com.spring.backproject.Models.DEV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface DevRepository extends JpaRepository<DEV,Long> {
   List<DEV> findByIdDev(Long idDev);
   @Query("SELECT DISTINCT d.nomDev FROM DEV d")
    List<String> findAllNomDev();
List<DEV> findAllByUtilisateurIdOrIdSiteOrCdcIdCDCOrMajIdMajOrVehidListCodeVeh(Long idRc, Integer idSite,
                                                                               Long idCDC, Long idMaj, Integer codeVeh);
List<DEV> findByNomDll(String nomDLL);

}

package com.spring.backproject.Repository;

import com.spring.backproject.Models.CDC;
import com.spring.backproject.Models.SITES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRep extends JpaRepository<SITES,Long> {
    @Query("SELECT DISTINCT s.nomSite from SITES s")
    List<String> findAllNomSite();
    List<CDC>findNomSiteByIdSite(Integer idSite);
    //String findNomSiteByIdSite(Integer idSite);
    Integer findIdSiteByNomSite(String nomSite);
}

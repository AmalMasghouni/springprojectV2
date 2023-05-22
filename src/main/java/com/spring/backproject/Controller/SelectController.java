package com.spring.backproject.Controller;

import com.spring.backproject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")


public class SelectController {
    @Autowired
    MarqueRepo marqueRepo;
    @Autowired
    CdcRep cdcRepository;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    MajRepository majRepository;
    @Autowired
    SiteRep siteRep;
    @Autowired
    UserRepository userRepository;
        @GetMapping("/selectSite")
    public List<String> selectSite(){
            List<String> nomSite=siteRep.findAllNomSite();
        return  nomSite;}
    @GetMapping("/selectMarque")
    public List<String> selectMarque(){
            List<String> nomMarque=vehiculeRepository.selectGroupeDeMarque();
        return nomMarque;
        }
    @GetMapping("/selectModeleByMarque/{marque}")
    public List<String> selectModeleByMarque(@PathVariable String marque) {
        List<String> nomModel = vehiculeRepository.selectModeleByMarque(marque);
        return nomModel;
    }
        @GetMapping("/selectModele")
    public List<String> selectModele(){
            List<String> nomModel=vehiculeRepository.selectModele();
            return nomModel;
        }
       @GetMapping("/selectVersion")
    public List<String> selectVersion(){
            List<String> nomVersion=majRepository.findAllNomMaj();
return  nomVersion;
        }
            @GetMapping("/selectCdc")
    public List<String> selectCDC(){
            List<String> refCdc=cdcRepository.findAllRefCDC();
           return refCdc; }
@GetMapping("/selectUtilisateur")
    public List<String> selectUser(){
                List<String> utilisateur=userRepository.findAllUtilisateur();
                return utilisateur;
}
}

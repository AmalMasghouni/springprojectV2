package com.spring.backproject.Service;

import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.SiteRep;
import com.spring.backproject.Repository.VersionMajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GestionDeveloppementService {
    @Autowired
    SiteRep siteRep;
    @Autowired
    VersionMajRepository versionMajRepository;
    public List<Map<String, Object>> createDevMapList(List<DEV> DEVList){
        List<Map<String, Object>> devMapList = new ArrayList<>();
        for (DEV dev : DEVList) {
            Map<String, Object> devMap = new HashMap<>();
            devMap.put("n", dev.getIdDev());
            devMap.put("nomDev",dev.getNomDev());
            if(dev.getCdc()!=null){
            devMap.put("cdc",dev.getCdc().getNomCDC());}
            devMap.put("idcdc",dev.getCdc().getIdCDC());
            if(dev.getCdc().getSignatureOk()!=null){
                if(dev.getCdc().getSignatureOk()==true){devMap.put("etatCdc","signé");}
                else {devMap.put("etatCdc","non signé");}
            }
            else{devMap.put("etatCdc","");}
            devMap.put("refCDCActia",dev.getCdc().getRefCDC());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String creationDate = dateFormat.format(dev.getCdc().getCreation());
            devMap.put("datacdc",creationDate);
            if (dev.getCdc().getReverse() != null) {
                devMap.put("source", dev.getCdc().getReverse().getNomReverse());
            } else {
                devMap.put("source","");
            }
            devMap.put("indice",dev.getCdc().getIndCDC());
            SITES SITESCDC = siteRep.findByIdSite(dev.getCdc().getIdSite().intValue());
            String nomSiteCDC = (SITESCDC != null) ? SITESCDC.getNomSite() : "";
            devMap.put("siteCDC",nomSiteCDC);
            devMap.put("nomDll",dev.getNomDll());
            devMap.put("menu",dev.getMenu().getPathMenu());
            devMap.put("idmenu",dev.getMenu().getId());
            devMap.put("utilisateur",dev.getUtilisateur().getFirstName()+' '+dev.getUtilisateur().getLastName());
            devMap.put("ecu",dev.getEcu().getNomEcu());
            devMap.put("maj",dev.getMaj().getNomMaj());
            SITES SITES = siteRep.findByIdSite(dev.getIdSite().intValue());
            String nomSite = (SITES != null) ? SITES.getNomSite() : "";
            //String site= siteRep.findNomSiteByIdSite( dev.getIdSite().longValue());
            devMap.put("idSite",nomSite);
            devMap.put("etatDev",dev.getEtatDev().getNomEtatDev());
            devMap.put("typeDev",dev.getTypeDev().getNomTypeDev());
            devMap.put("refCdc",dev.getCdc().getRefCDC());
            devMap.put("famille",dev.getEcu().getFamille().getNomFamille());
            devMap.put("bug",dev.getNumBugzilla());

            List<String> validation= new ArrayList<>();
            for(Validation v: dev.getValidationList()){
                if(v.getEtatValid()=="GT") {v.setEtatValid("test unitaire"); }
                else {v.setEtatValid("Intégration");}
                VERSION_MAJ vers=versionMajRepository.findByIdVer(v.getIdValid().longValue());
                validation.add(v.getDateValid()+'-'+v.getEtatValid()+'-'+vers);
            }
            devMap.put("validation",validation);
            devMap.put("commentaire",dev.getDevComment());
            List<String> nomFonction=new ArrayList<>();
            for(Fonction f: dev.getFonctionList()){
                nomFonction.add(f.getNomFonction());
            }

            devMap.put("fonction",nomFonction);
            List<String> fonctioncdc=new ArrayList<>();
            for(Fonction f: dev.getCdc().getFonctionList()){
                fonctioncdc.add(f.getNomFonction());
            }
            devMap.put("fonctionCDC",fonctioncdc);
            List<String> nomVehList = new ArrayList<>();
            for (Vehid vehid : dev.getVehidList()) {
                nomVehList.add(vehid.getNomVeh()+'['+vehid.getNomInterne()+']');
            }
            devMap.put("vehidList", nomVehList);

            devMapList.add(devMap);
        }
        return devMapList;
    }
}

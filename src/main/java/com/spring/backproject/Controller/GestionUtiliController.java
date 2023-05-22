package com.spring.backproject.Controller;

import com.spring.backproject.Exception.ResourceNotFoundException;
import com.spring.backproject.Models.*;
import com.spring.backproject.Repository.SiteRep;
import com.spring.backproject.Repository.UserRepository;
import com.spring.backproject.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.collect;



@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class GestionUtiliController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    SiteRep siteReposi;

    @GetMapping("/getutliBysite")
    public ResponseEntity<List<Map<String, Object>>> getutliBysite() {
        List<Utilisateur> utilisateurs= userRepository.findAll();

        List<Map<String, Object>> utlisaMap = utilisateurs.stream()
                .map(utlMap-> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id",utlMap.getId());
                    map.put("firstName",utlMap.getFirstName());
                    map.put("lastName", utlMap.getLastName());
                    map.put("responsableequipe", utlMap.getResponsableequipe());
                    map.put("role", utlMap.getRole());
                    map.put("email", utlMap.getEmail());
                    map.put("employeecode", utlMap.getEmployeecode());

                    if (utlMap.getSites() != null) {
                        map.put("site", utlMap.getSites().getNomSite());
                    } else {
                        map.put("site", "");
                    }
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(utlisaMap);
    }

    @GetMapping("/getAllUtilisateur")
    public ResponseEntity<?>getAllUtil(){
        List<Utilisateur> utilisateurs=userRepository.findAll();
        return ResponseEntity.ok(utilisateurs);}

    @DeleteMapping("/delete-utilisateur/{id}")

    public ResponseEntity<?> deleteEmployee (@PathVariable("id") Long id){

        Utilisateur utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));

        // Dissocier les objets DEV de l'utilisateur
        List<DEV> DEVList = utilisateur.getDEVList();
        for (DEV dev : DEVList) {
            dev.setUtilisateur(null);
        }

        // Supprimer l'utilisateur
        userRepository.delete(utilisateur);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/modifier-utilisateur/{id}")
    public ResponseEntity<Utilisateur> updateEmployee(@PathVariable("id") Long id,@RequestBody RegisterDto registerDto) {
       Utilisateur user = userRepository.findById(id).orElse(null);

       // user.setName(registerDto.getName());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setResponsableequipe(registerDto.getResponsableequipe());
        user.setRole(registerDto.getRole());

        user.setEmployeecode(registerDto.getEmployeecode());
        if (registerDto.getSite() != null) {
            SITES SITES = siteReposi.findByNomSite(registerDto.getSite());
            user.setSites(SITES);
        }
        Utilisateur updateEmployee = userRepository.save(user);
        return ResponseEntity.ok(updateEmployee);


    }
     @GetMapping("/getbyid/{id}")
     public ResponseEntity<List<Map<String, Object>>> getVehiculebuid(@PathVariable Long id ){

        Optional<Utilisateur > user = userRepository.findById(id);

           List<Map<String, Object>> utlisaMap = user.stream()
                   .map(utlMap-> {
                       Map<String, Object> map = new HashMap<>();

                       map.put("firstName",utlMap.getFirstName());
                       map.put("lastName", utlMap.getLastName());
                       map.put("responsableequipe", utlMap.getResponsableequipe());
                       map.put("role", utlMap.getRole());
                       map.put("email", utlMap.getEmail());
                       map.put("employeecode", utlMap.getEmployeecode());

                       if (utlMap.getSites() != null) {
                           map.put("site", utlMap.getSites().getNomSite());
                       } else {
                           map.put("site", "");
                       }
                       return map;
                   })
                   .collect(Collectors.toList());

         return ResponseEntity.ok(utlisaMap);  }

}

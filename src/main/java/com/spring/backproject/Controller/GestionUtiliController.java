package com.spring.backproject.Controller;

import com.spring.backproject.Models.RegisterDto;
import com.spring.backproject.Models.Utilisateur;
import com.spring.backproject.Models.Vehid;
import com.spring.backproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/auth")
public class GestionUtiliController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;



    @GetMapping("/getAllUtilisateur")
    public ResponseEntity<?>getAllUtil(){
        List<Utilisateur> utilisateurs=userRepository.findAll();
        return ResponseEntity.ok(utilisateurs);}

    @DeleteMapping("/delete-utilisateur/{id}")
    public ResponseEntity<?> deleteEmployee (@PathVariable("id") Long id){
        userRepository.deleteById(id);
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
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmployeecode(registerDto.getEmployeecode());

        Utilisateur updateEmployee = userRepository.save(user);
        return ResponseEntity.ok(updateEmployee);


    }
     @GetMapping("getbyid/{id}")
     public ResponseEntity<?> getVehiculebuid(@PathVariable Long id ){

         Utilisateur user = userRepository.findById(id).orElse(null);
         return new ResponseEntity<>(user, HttpStatus.OK);  }
}

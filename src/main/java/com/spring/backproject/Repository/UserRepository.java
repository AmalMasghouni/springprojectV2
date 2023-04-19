package com.spring.backproject.Repository;

import com.spring.backproject.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<Utilisateur,Long> {
boolean existsByEmail(String email);
Utilisateur findByEmail(String email);
    @Query("SELECT DISTINCT CONCAT(u.firstName, ' ', u.lastName) FROM Utilisateur u")
    List<String> findAllUtilisateur();
}

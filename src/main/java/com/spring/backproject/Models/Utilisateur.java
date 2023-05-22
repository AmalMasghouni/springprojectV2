package com.spring.backproject.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@NotNull
    private String firstName;
@NotNull
    private String lastName;
@NotNull
    @Column(unique = true)
    @Email(message = "L'adresse e-mail doit être valide.")
    @Pattern(regexp = "^[a-z0-9._%+-]+@actia-engineering\\.tn$", message = "L'adresse e-mail doit être sous la forme @actia-engineering.com.")
    private String email;
@NotNull
@Size(min = 8)
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    private String password;
@Column(name = "responsableEqu")
    private String responsableequipe;
@Column(name="role")
    private String role;
@Column(name="employeCode")
    private String employeecode;

    @Column(name = "first_login",nullable = false)
    private boolean firstLogin=false;

    public boolean isFirstLogin() {
        return firstLogin;
    }
@ManyToOne(fetch = FetchType.LAZY)
    private SITES sites;
    @OneToMany(fetch = FetchType.LAZY)
    private List<DEV> DEVList;

}

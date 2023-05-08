package com.spring.backproject.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
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
    private String responsableequipe;
    private String role;
    private String employeecode;
    private boolean firstLogin;
}

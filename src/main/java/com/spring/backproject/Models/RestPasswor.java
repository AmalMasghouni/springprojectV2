package com.spring.backproject.Models;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RestPasswor {
    @NotNull

    @Email(message = "L'adresse e-mail doit être valide.")
    @Pattern(regexp = "^[a-z0-9._%+-]+@actia-engineering\\.tn$", message = "L'adresse e-mail doit être sous la forme @actia-engineering.com.")
    String email;
}

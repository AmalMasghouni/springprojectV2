package com.spring.backproject.Models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Changemdp {
String email;
    String mdpgenere;
    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    String nouveaumdp;
    String resetnouveaumdp;
}

package com.spring.backproject.Models;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltreResponseDto {
    private List<Integer> codesVehicules;
    private List<Long> codeMaj;
    private List<Integer> codeSite;
    private List<Long> codeuser;
    private List<Long> codeCdc;
}

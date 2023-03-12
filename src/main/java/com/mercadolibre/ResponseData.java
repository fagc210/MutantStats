package com.mercadolibre;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseData {
    private int count_mutant_dna;
    private int count_human_dna;
    private Double ratio;
}

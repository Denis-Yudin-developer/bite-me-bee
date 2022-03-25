package ru.coderiders.Generator.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorHiveRqDto {

    private Long id;

    private Double honeyCapacity;
}

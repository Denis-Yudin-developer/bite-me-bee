package ru.coderiders.bitemebee.data;

import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;

import java.util.List;

import static ru.coderiders.bitemebee.data.BeeFamilyData.BEE_FAMILY_RS_DTO_1;
import static ru.coderiders.bitemebee.data.BeeFamilyData.BEE_FAMILY_RS_DTO_2;

public class HiveData {
    public static final HiveRqDto HIVE_RQ_DTO_1 = HiveRqDto.builder()
            .name("Большой ж")
            .frameCount(8)
            .build();

    public static final HiveRsDto HIVE_RS_DTO_1 = HiveRsDto.builder()
            .id(1L)
            .beeFamilies(List.of(BEE_FAMILY_RS_DTO_1))
            .name("Большой ж")
            .frameCount(8)
            .honeyAmount(4.9)
            .build();

    public static final HiveRsDto HIVE_RS_DTO_2 = HiveRsDto.builder()
            .id(2L)
            .beeFamilies(List.of(BEE_FAMILY_RS_DTO_2))
            .name("Малый ж")
            .frameCount(10)
            .honeyAmount(7.2)
            .build();
}

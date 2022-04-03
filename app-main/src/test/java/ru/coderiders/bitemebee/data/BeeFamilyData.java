package ru.coderiders.bitemebee.data;

import ru.coderiders.bitemebee.rest.dto.BeeFamilyRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRsDto;

import static ru.coderiders.bitemebee.data.BeeTypeData.BEE_TYPE_RS_DTO_1;
import static ru.coderiders.bitemebee.data.BeeTypeData.BEE_TYPE_RS_DTO_2;

public class BeeFamilyData {
    public static final BeeFamilyRqDto BEE_FAMILY_RQ_DTO_1 = BeeFamilyRqDto.builder()
            .beeTypeId(1L)
            .hiveId(1L)
            .note("Купили на Авито. Продавец Александр, 8(903)123-45-67")
            .dronePopulation(0L)
            .workerPopulation(99L)
            .queenPopulation(1L)
            .build();

    public static final BeeFamilyRsDto BEE_FAMILY_RS_DTO_1 = BeeFamilyRsDto.builder()
            .id(1L)
            .beeType(BEE_TYPE_RS_DTO_1)
            .note("Купили на Авито. Продавец Александр, 8(903)123-45-67")
            .isAlive(true)
            .dronePopulation(0L)
            .workerPopulation(99L)
            .queenPopulation(1L)
            .population(100L)
            .build();

    public static final BeeFamilyRsDto BEE_FAMILY_RS_DTO_2 = BeeFamilyRsDto.builder()
            .id(2L)
            .beeType(BEE_TYPE_RS_DTO_2)
            .note("Купили на Авито. Продавец Cергей, 8(901)987-76-54")
            .isAlive(true)
            .dronePopulation(20L)
            .workerPopulation(119L)
            .queenPopulation(1L)
            .population(140L)
            .build();
}

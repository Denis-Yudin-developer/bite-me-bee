package ru.coderiders.bitemebee.data;

import ru.coderiders.bitemebee.rest.dto.BeeTypeRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;

public class BeeTypeData {
    public static final BeeTypeRqDto BEE_TYPE_RQ_DTO_1 = BeeTypeRqDto.builder()
            .title("Медоносная пчела")
            .description("Люди разводят медоносных пчёл для получения продуктов пчеловодства: воска, мёда и других.")
            .minCo2(310)
            .maxCo2(640)
            .minHumidity(75)
            .maxHumidity(83)
            .minTemperature(27)
            .maxTemperature(34)
            .coldResistance(0.9)
            .diseaseResistance(0.9)
            .honeyProductivity(0.9)
            .eggProductivity(0.9)
            .aggressionLevel(0.9)
            .roilingLevel(0.9)
            .build();

    public static final BeeTypeRsDto BEE_TYPE_RS_DTO_1 = BeeTypeRsDto.builder()
            .id(1L)
            .title("Медоносная пчела")
            .description("Люди разводят медоносных пчёл для получения продуктов пчеловодства: воска, мёда и других.")
            .minCo2(310)
            .maxCo2(640)
            .minHumidity(75)
            .maxHumidity(83)
            .minTemperature(27)
            .maxTemperature(34)
            .coldResistance(0.9)
            .diseaseResistance(0.9)
            .honeyProductivity(0.9)
            .eggProductivity(0.9)
            .aggressionLevel(0.9)
            .roilingLevel(0.9)
            .build();

    public static final BeeTypeRsDto BEE_TYPE_RS_DTO_2 = BeeTypeRsDto.builder()
            .id(2L)
            .title("Лесная пчела")
            .description("Люди разводят лесных пчёл для получения продуктов пчеловодства: лесного воска, мёда и друг.")
            .minCo2(200)
            .maxCo2(520)
            .minHumidity(60)
            .maxHumidity(99)
            .minTemperature(25)
            .maxTemperature(33)
            .coldResistance(1.2)
            .diseaseResistance(1.2)
            .honeyProductivity(1.2)
            .eggProductivity(1.2)
            .aggressionLevel(1.2)
            .roilingLevel(1.2)
            .build();
}

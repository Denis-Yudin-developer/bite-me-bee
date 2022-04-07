package ru.coderiders.bitemebee.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.coderiders.bitemebee.rest.api.impl.HiveController;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.service.impl.HiveServiceImpl;
import ru.coderiders.bitemebee.service.impl.HiveSnapshotServiceImpl;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.coderiders.bitemebee.converter.ObjectToJsonConverter.objectToJsonString;
import static ru.coderiders.bitemebee.data.HiveData.HIVE_RQ_DTO_1;
import static ru.coderiders.bitemebee.data.HiveData.HIVE_RS_DTO_1;
import static ru.coderiders.bitemebee.data.HiveData.HIVE_RS_DTO_2;
import static ru.coderiders.bitemebee.data.HiveSnapshotData.HIVE_SNAPSHOT_RQ_DTO_1;
import static ru.coderiders.bitemebee.data.HiveSnapshotData.HIVE_SNAPSHOT_RS_DTO_1;
import static ru.coderiders.bitemebee.data.HiveSnapshotData.HIVE_SNAPSHOT_RS_DTO_2;

@WebMvcTest(HiveController.class)
public class HiveControllerTest {
    @MockBean
    private HiveServiceImpl hiveService;
    @MockBean
    private HiveSnapshotServiceImpl hiveSnapshotService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSnapshots_validData_returnOk() throws Exception {
        List<HiveSnapshotGeneratorDto> hiveSnapshotGeneratorDtoList = Arrays.asList(HIVE_SNAPSHOT_RS_DTO_1, HIVE_SNAPSHOT_RS_DTO_2);
        when(hiveSnapshotService.getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1)).thenReturn(hiveSnapshotGeneratorDtoList);
        mockMvc.perform(post("/api/hives/snapshots")
                        .content(objectToJsonString(HIVE_SNAPSHOT_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(hiveSnapshotGeneratorDtoList)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].hiveId").value(1));
        verify(hiveSnapshotService, times(1)).getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1);
    }

    @Test
    public void getSnapshots_invalidData_returnNotFound() throws Exception {
        when(hiveSnapshotService.getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1)).thenThrow(new NotFoundException("Улей не найден"));
        mockMvc.perform(post("/api/hives/snapshots")
                        .content(objectToJsonString(HIVE_SNAPSHOT_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(hiveSnapshotService, times(1)).getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1);
    }

    @Test
    public void getAll_validData_returnOk() throws Exception {
        Page<HiveRsDto> hiveRsDtoPage = new PageImpl<>(Arrays.asList(HIVE_RS_DTO_1, HIVE_RS_DTO_2));
        when(hiveService.getAll(PageRequest.of(0, 20))).thenReturn(hiveRsDtoPage);
        mockMvc.perform(get("/api/hives"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(hiveRsDtoPage)))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].beeFamilies[0].beeType.title")
                        .value("Медоносная пчела"));
        verify(hiveService, times(1)).getAll(PageRequest.of(0, 20));
    }

    @Test
    public void getById_validData_returnOk() throws Exception {
        when(hiveService.getById(1L)).thenReturn(HIVE_RS_DTO_1);
        mockMvc.perform(get("/api/hives/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(HIVE_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.beeFamilies[0].isAlive").isBoolean())
                .andExpect(jsonPath("$.beeFamilies[0].beeType.title")
                        .value("Медоносная пчела"));
        verify(hiveService, times(1)).getById(1L);
    }

    @Test
    public void getById_invalidData_returnNotFound() throws Exception {
        when(hiveService.getById(10L)).thenThrow(new NotFoundException("Улей с id=10 не найден"));
        mockMvc.perform(get("/api/hives/10"))
                .andExpect(status().isNotFound());
        verify(hiveService, times(1)).getById(10L);
    }

    @Test
    public void create_validData_returnCreated() throws Exception {
        when(hiveService.create(HIVE_RQ_DTO_1)).thenReturn(HIVE_RS_DTO_1);
        mockMvc.perform(post("/api/hives/")
                        .content(objectToJsonString(HIVE_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectToJsonString(HIVE_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.beeFamilies[0].isAlive").isBoolean())
                .andExpect(jsonPath("$.beeFamilies[0].beeType.title")
                        .value("Медоносная пчела"));
        verify(hiveService, times(1)).create(HIVE_RQ_DTO_1);
    }

    @Test
    public void create_invalidData_returnBadRequest() throws Exception {
        when(hiveService.create(HIVE_RQ_DTO_1))
                .thenThrow(new BadRequestException("Ошибка в теле запроса при добавлении улья"));
        mockMvc.perform(post("/api/hives/")
                        .content(objectToJsonString(HIVE_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(hiveService, times(1)).create(HIVE_RQ_DTO_1);
    }
}

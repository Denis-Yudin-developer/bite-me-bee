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
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.coderiders.bitemebee.converter.ObjectToJsonConverter.objectToJsonString;
import static ru.coderiders.bitemebee.data.HiveData.*;
import static ru.coderiders.bitemebee.data.HiveSnapshotData.*;

@WebMvcTest(HiveController.class)
public class HiveControllerTest {
    @MockBean
    private HiveServiceImpl hiveService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSnapshotsOkTest() throws Exception {
        List<HiveSnapshotGeneratorDto> hiveSnapshotGeneratorDtoList = Arrays.asList(HIVE_SNAPSHOT_RS_DTO_1, HIVE_SNAPSHOT_RS_DTO_2);
        when(hiveService.getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1)).thenReturn(hiveSnapshotGeneratorDtoList);
        mockMvc.perform(get("/api/hives/snapshots")
                        .content(objectToJsonString(HIVE_SNAPSHOT_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(hiveSnapshotGeneratorDtoList)));
        verify(hiveService, times(1)).getSnapshots(HIVE_SNAPSHOT_RQ_DTO_1);
    }

    @Test
    public void getSnapshotsBadRequestTest() throws Exception {
        mockMvc.perform(get("/api/hives/snapshots"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllOKTest() throws Exception {
        Page<HiveRsDto> hiveRsDtoPage = new PageImpl<>(Arrays.asList(HIVE_RS_DTO_1, HIVE_RS_DTO_2));
        when(hiveService.getAll(PageRequest.of(0, 20))).thenReturn(hiveRsDtoPage);
        mockMvc.perform(get("/api/hives"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(hiveRsDtoPage)));
        verify(hiveService, times(1)).getAll(PageRequest.of(0, 20));
    }

    @Test
    public void getOkTest() throws Exception {
        when(hiveService.getById(1L)).thenReturn(HIVE_RS_DTO_1);
        mockMvc.perform(get("/api/hives/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(HIVE_RS_DTO_1)));
        verify(hiveService, times(1)).getById(1L);
    }

    @Test
    public void getNotFoundTest() throws Exception {
        when(hiveService.getById(10L)).thenThrow(new NotFoundException("Улей с id=10 не найден"));
        mockMvc.perform(get("/api/hives/10"))
                .andExpect(status().isNotFound());
        verify(hiveService, times(1)).getById(10L);
    }

    @Test
    public void getBadRequestTest() throws Exception {
        when(hiveService.getById(-1L)).thenThrow(new BadRequestException("Ошибка запроса улья по id=-1"));
        mockMvc.perform(get("/api/hives/-1"))
                .andExpect(status().isBadRequest());
        verify(hiveService, times(1)).getById(-1L);
    }

    @Test
    public void createCreatedTest() throws Exception {
        when(hiveService.create(HIVE_RQ_DTO_1)).thenReturn(HIVE_RS_DTO_1);
        mockMvc.perform(post("/api/hives/")
                        .content(objectToJsonString(HIVE_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectToJsonString(HIVE_RS_DTO_1)));
        verify(hiveService, times(1)).create(HIVE_RQ_DTO_1);
    }

    @Test
    public void createBadRequestTest() throws Exception {
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

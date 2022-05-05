package ru.coderiders.bitemebee.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import ru.coderiders.bitemebee.rest.api.impl.BeeFamilyController;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRsDto;
import ru.coderiders.bitemebee.service.BeeFamilyService;
import ru.coderiders.bitemebee.service.BeeFamilySnapshotService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.coderiders.bitemebee.converter.ObjectToJsonConverter.toJsonString;
import static ru.coderiders.bitemebee.data.BeeFamilyData.BEE_FAMILY_RQ_DTO_1;
import static ru.coderiders.bitemebee.data.BeeFamilyData.BEE_FAMILY_RS_DTO_1;
import static ru.coderiders.bitemebee.data.BeeFamilyData.BEE_FAMILY_RS_DTO_2;
import static ru.coderiders.bitemebee.data.BeeFamilySnapshotData.BEE_FAMILY_SNAPSHOT_DTO_1;
import static ru.coderiders.bitemebee.data.BeeFamilySnapshotData.BEE_FAMILY_SNAPSHOT_DTO_2;
import static ru.coderiders.bitemebee.data.BeeFamilySnapshotData.BEE_FAMILY_SNAPSHOT_RQ_DTO_1;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class BeeFamilyControllerTest {
    @MockBean
    private BeeFamilyService beeFamilyService;
    @MockBean
    private BeeFamilySnapshotService beeFamilySnapshotService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSnapshots_validData_returnOk() throws Exception {
        Page<BeeFamilySnapshotDto> familySnapshotDtoDtoList =
                new PageImpl<>(Arrays.asList(BEE_FAMILY_SNAPSHOT_DTO_1, BEE_FAMILY_SNAPSHOT_DTO_2));
        when(beeFamilySnapshotService.getSnapshots(PageRequest.of(0, 20), BEE_FAMILY_SNAPSHOT_RQ_DTO_1))
                .thenReturn(familySnapshotDtoDtoList);
        mockMvc.perform(post("/api/bee_families/snapshots")
                        .content(toJsonString(BEE_FAMILY_SNAPSHOT_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(familySnapshotDtoDtoList)))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].familyId").value(1));
        verify(beeFamilySnapshotService, times(1))
                .getSnapshots(PageRequest.of(0, 20), BEE_FAMILY_SNAPSHOT_RQ_DTO_1);
    }

    @Test
    public void getSnapshots_invalidData_returnNotFound() throws Exception {
        when(beeFamilySnapshotService.getSnapshots(PageRequest.of(0, 20), BEE_FAMILY_SNAPSHOT_RQ_DTO_1))
                .thenThrow(new NotFoundException("Семья не найдена"));
        mockMvc.perform(post("/api/bee_families/snapshots")
                        .content(toJsonString(BEE_FAMILY_SNAPSHOT_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(beeFamilySnapshotService, times(1))
                .getSnapshots(PageRequest.of(0, 20), BEE_FAMILY_SNAPSHOT_RQ_DTO_1);
    }

    @Test
    public void getAll_validData_returnOk() throws Exception {
        Page<BeeFamilyRsDto> beeFamilyRsDtoPage = new PageImpl<>(Arrays.asList(BEE_FAMILY_RS_DTO_1, BEE_FAMILY_RS_DTO_2));
        when(beeFamilyService.getAll(PageRequest.of(0, 20))).thenReturn(beeFamilyRsDtoPage);
        mockMvc.perform(get("/api/bee_families"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(beeFamilyRsDtoPage)))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].beeType.title").value("Медоносная пчела"));
        verify(beeFamilyService, times(1)).getAll(PageRequest.of(0, 20));
    }

    @Test
    public void getById_validData_returnOk() throws Exception {
        when(beeFamilyService.getById(1L)).thenReturn(BEE_FAMILY_RS_DTO_1);
        mockMvc.perform(get("/api/bee_families/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(BEE_FAMILY_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isAlive").isBoolean())
                .andExpect(jsonPath("$.beeType.title").value("Медоносная пчела"));
        verify(beeFamilyService, times(1)).getById(1L);
    }

    @Test
    public void getById_invalidData_returnNotFound() throws Exception {
        when(beeFamilyService.getById(10L)).thenThrow(new NotFoundException("Семья с id=10 не найдена"));
        mockMvc.perform(get("/api/bee_families/10"))
                .andExpect(status().isNotFound());
        verify(beeFamilyService, times(1)).getById(10L);
    }

    @Test
    public void create_validData_returnCreated() throws Exception {
        when(beeFamilyService.create(BEE_FAMILY_RQ_DTO_1)).thenReturn(BEE_FAMILY_RS_DTO_1);
        mockMvc.perform(post("/api/bee_families/")
                        .content(toJsonString(BEE_FAMILY_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(toJsonString(BEE_FAMILY_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isAlive").isBoolean())
                .andExpect(jsonPath("$.beeType.title").value("Медоносная пчела"));
        verify(beeFamilyService, times(1)).create(BEE_FAMILY_RQ_DTO_1);
    }

    @Test
    public void create_invalidData_returnBadRequest() throws Exception {
        when(beeFamilyService.create(BEE_FAMILY_RQ_DTO_1))
                .thenThrow(new BadRequestException("Ошибка в теле запроса при добавлении семьи"));
        mockMvc.perform(post("/api/bee_families/")
                        .content(toJsonString(BEE_FAMILY_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(beeFamilyService, times(1)).create(BEE_FAMILY_RQ_DTO_1);
    }
}

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
import ru.coderiders.bitemebee.rest.api.impl.BeeTypeController;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;
import ru.coderiders.bitemebee.service.impl.BeeTypeServiceImpl;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.coderiders.bitemebee.converter.ObjectToJsonConverter.objectToJsonString;
import static ru.coderiders.bitemebee.data.BeeTypeData.*;

@WebMvcTest(BeeTypeController.class)
public class BeeTypeControllerTest {
    @MockBean
    private BeeTypeServiceImpl beeTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll_validData_returnOk() throws Exception {
        Page<BeeTypeRsDto> beeTypeRsDtoPage = new PageImpl<>(Arrays.asList(BEE_TYPE_RS_DTO_1, BEE_TYPE_RS_DTO_2));
        when(beeTypeService.getAll(PageRequest.of(0, 20))).thenReturn(beeTypeRsDtoPage);
        mockMvc.perform(get("/api/bee_types"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(beeTypeRsDtoPage)))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].title").value("Медоносная пчела"));
        verify(beeTypeService, times(1)).getAll(PageRequest.of(0, 20));
    }

    @Test
    public void getById_validData_returnOk() throws Exception {
        when(beeTypeService.getById(1L)).thenReturn(BEE_TYPE_RS_DTO_1);
        mockMvc.perform(get("/api/bee_types/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectToJsonString(BEE_TYPE_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Медоносная пчела"));
        verify(beeTypeService, times(1)).getById(1L);
    }

    @Test
    public void getById_invalidData_returnNotFound() throws Exception {
        when(beeTypeService.getById(10L)).thenThrow(new NotFoundException("Вид пчёл с id=10 не найден"));
        mockMvc.perform(get("/api/bee_types/10"))
                .andExpect(status().isNotFound());
        verify(beeTypeService, times(1)).getById(10L);
    }

    @Test
    public void create_validData_returnCreated() throws Exception {
        when(beeTypeService.create(BEE_TYPE_RQ_DTO_1)).thenReturn(BEE_TYPE_RS_DTO_1);
        mockMvc.perform(post("/api/bee_types/")
                        .content(objectToJsonString(BEE_TYPE_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectToJsonString(BEE_TYPE_RS_DTO_1)))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Медоносная пчела"));
        verify(beeTypeService, times(1)).create(BEE_TYPE_RQ_DTO_1);
    }

    @Test
    public void create_invalidData_returnBadRequest() throws Exception {
        when(beeTypeService.create(BEE_TYPE_RQ_DTO_1))
                .thenThrow(new BadRequestException("Вид пчёл с таким названием уже существует"));
        mockMvc.perform(post("/api/bee_types/")
                        .content(objectToJsonString(BEE_TYPE_RQ_DTO_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(beeTypeService, times(1)).create(BEE_TYPE_RQ_DTO_1);
    }
}

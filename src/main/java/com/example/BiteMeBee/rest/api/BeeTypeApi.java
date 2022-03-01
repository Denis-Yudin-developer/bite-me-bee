package com.example.BiteMeBee.rest.api;

import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/bee-type")
@Tag(name = "BeeType", description = "API вида пчёл")
public interface BeeTypeApi {

  @GetMapping("/bee-types")
  @Operation(summary = "Получить все виды пчёл", tags = "beeType")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Виды пчёл найдены",
      content = {
        @Content(
          mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = BeeTypeRsDto.class)))
      })
  })
  List<BeeTypeRsDto> getAllMessages(Pageable pageable);
}

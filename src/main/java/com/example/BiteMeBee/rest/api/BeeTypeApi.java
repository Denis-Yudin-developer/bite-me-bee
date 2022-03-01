package com.example.BiteMeBee.rest.api;

import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bee-type")
@Tag(name = "BeeType", description = "API вида пчёл")
public interface BeeTypeApi {

  @GetMapping("/bee-types")
  public List<BeeTypeRsDto> getAllMessages();
}

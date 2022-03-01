package com.example.BiteMeBee.rest.api.impl;

import com.example.BiteMeBee.rest.api.BeeTypeApi;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import com.example.BiteMeBee.service.impl.BeeTypeServiceImpl;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeeTypeController implements BeeTypeApi {

  private final BeeTypeServiceImpl beeTypeService;

  public BeeTypeController(BeeTypeServiceImpl beeTypeService) {
    this.beeTypeService = beeTypeService;
  }

  @Override
  public List<BeeTypeRsDto> getAllMessages(Pageable pageable) {
    return beeTypeService.getAllBeeTypes(pageable);
  }
}

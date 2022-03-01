package com.example.BiteMeBee.service;

import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BeeTypeService {

  List<BeeTypeRsDto> getAllBeeTypes(Pageable pageable);
}

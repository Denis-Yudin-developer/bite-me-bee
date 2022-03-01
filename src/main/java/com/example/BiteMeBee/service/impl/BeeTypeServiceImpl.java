package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.mapper.BeeTypeMapper;
import com.example.BiteMeBee.repository.BeeTypeRepository;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import com.example.BiteMeBee.service.BeeTypeService;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeeTypeServiceImpl implements BeeTypeService {

  private final BeeTypeRepository beeTypeRepository;

  private final BeeTypeMapper beeTypeMapper;

  public BeeTypeServiceImpl(BeeTypeRepository beeTypeRepository,
    BeeTypeMapper beeTypeMapper) {
    this.beeTypeRepository = beeTypeRepository;
    this.beeTypeMapper = beeTypeMapper;
  }

  @Override
  public List<BeeTypeRsDto> getAllBeeTypes(Pageable pageable) {
    return beeTypeMapper.toDtoList(beeTypeRepository.findAll(pageable).getContent());
  }
}

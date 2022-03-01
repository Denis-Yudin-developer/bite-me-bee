package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.repository.BeeTypeRepository;
import com.example.BiteMeBee.service.BeeTypeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BeeTypeServiceImpl implements BeeTypeService {

  private final BeeTypeRepository beeTypeRepository;

  public BeeTypeServiceImpl(BeeTypeRepository beeTypeRepository) {
    this.beeTypeRepository = beeTypeRepository;
  }

  @Override
  public List<BeeType> getAllBeeTypes() {
    return beeTypeRepository.findAll();
  }
}

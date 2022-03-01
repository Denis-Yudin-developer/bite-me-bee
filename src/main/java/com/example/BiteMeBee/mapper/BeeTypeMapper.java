package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeeTypeMapper {

  private final ModelMapper modelMapper;

  public BeeType toEntity(BeeTypeRqDto beeTypeRqDto) {
    return modelMapper.map(beeTypeRqDto, BeeType.class);
  }

  public BeeTypeRsDto toDto(BeeType beeType) {
    return modelMapper.map(beeType, BeeTypeRsDto.class);
  }

  public List<BeeTypeRsDto> toDtoList(List<BeeType> beeType) {
    return modelMapper.map(beeType, new TypeToken<List<BeeTypeRsDto>>() {}.getType());
  }
}

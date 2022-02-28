package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO пчелиной семьи")
public class BeeFamilyRqDto {

  //Подразумеваю, что пользователь на фронте будет выбирать вид пчёл(название вида уникально)
  // поменять на id
  @Size(max = 100, message
    = "Длинна названия вида должна быть в промежутке от 1 до 100 символов")
  @NotNull(message = "Не указано название вида")
  @Schema(description = "Название вида пчелиной семьи")
  private String beeTypeTitle;

  @Size(max = 1000, message
    = "Длинна заметки должна быть в промежутке от 1 до 100 символов")
  @NotNull(message = "Не указана заметка о пчелиной семье")
  @Schema(description = "Заметка о пчелиной семье")
  private String note;

  @NotNull(message = "Не указан статус жизни пчёл")
  @Schema(description = "Живая ли пчелиная семья")
  private Boolean status;
}

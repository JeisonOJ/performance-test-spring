package com.jeison.perfomance_test.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionQuestionReq {

    @NotBlank(message = "text required")
    private String text;
    @NotNull(message = "active required")
    private Boolean isActive;

}

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
public class SurveyReq {

    @NotBlank(message = "title required")
    private String title;
    private String description;
    @NotNull(message = "active required")
    private Boolean isActive;
    @NotNull(message = "user id required")
    private Long userId;

}

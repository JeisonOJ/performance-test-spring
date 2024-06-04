package com.jeison.perfomance_test.api.dto.request;

import java.util.List;

import com.jeison.perfomance_test.utils.enums.TypeQuestion;

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
public class QuestionReq {

    @NotBlank(message = "text required")
    private String text;
    @NotNull(message = "type required")
    private TypeQuestion type;
    @NotNull(message = "active required")
    private Boolean isActive;
    private List<OptionQuestionReq> options;
}

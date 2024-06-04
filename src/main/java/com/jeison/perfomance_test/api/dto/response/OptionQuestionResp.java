package com.jeison.perfomance_test.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionQuestionResp {

    private Long id;
    private String text;
    private Boolean isActive;

}

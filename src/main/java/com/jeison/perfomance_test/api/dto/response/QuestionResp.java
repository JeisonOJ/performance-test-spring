package com.jeison.perfomance_test.api.dto.response;

import java.util.List;

import com.jeison.perfomance_test.utils.enums.TypeQuestion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResp {

    private Long id;
    private String text;
    private TypeQuestion type;
    private Boolean isActive;
    private SurveyResp surveyResp;
    private List<OptionQuestionResp> options;

}

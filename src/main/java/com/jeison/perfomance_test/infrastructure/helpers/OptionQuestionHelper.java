package com.jeison.perfomance_test.infrastructure.helpers;

import com.jeison.perfomance_test.api.dto.request.OptionQuestionReq;
import com.jeison.perfomance_test.api.dto.response.OptionQuestionResp;
import com.jeison.perfomance_test.domain.entities.OptionQuestion;

public class OptionQuestionHelper {

    public static OptionQuestionResp questionToResp(OptionQuestion option) {
        return OptionQuestionResp.builder()
                .id(option.getId())
                .text(option.getText())
                .isActive(option.getIsActive())
                .build();
    }

    public static OptionQuestion reqToQuestion(OptionQuestionReq optionReq) {
        return OptionQuestion.builder()
                .text(optionReq.getText())
                .isActive(optionReq.getIsActive())
                .build();
    }
}

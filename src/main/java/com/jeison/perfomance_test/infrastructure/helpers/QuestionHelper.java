package com.jeison.perfomance_test.infrastructure.helpers;

import java.util.ArrayList;

import com.jeison.perfomance_test.api.dto.request.QuestionReq;
import com.jeison.perfomance_test.api.dto.response.QuestionResp;
import com.jeison.perfomance_test.domain.entities.Question;
import com.jeison.perfomance_test.utils.enums.TypeQuestion;

public class QuestionHelper {

    public static QuestionResp questionToResp(Question question) {
        if (question.getType().equals(TypeQuestion.OPEN)) {
            return QuestionResp.builder()
                .id(question.getId())
                .text(question.getText())
                .type(question.getType())
                .isActive(question.getIsActive())
                .options(new ArrayList<>())
                .surveyResp(SurveyHelper.surveyToResp(question.getSurvey()))
                .build();
        }
        return QuestionResp.builder()
                .id(question.getId())
                .text(question.getText())
                .type(question.getType())
                .isActive(question.getIsActive())
                .options(question.getOptions().stream().map(option-> OptionQuestionHelper.questionToResp(option)).toList())
                .surveyResp(SurveyHelper.surveyToResp(question.getSurvey()))
                .build();
    }

    public static Question reqToQuestion(QuestionReq questionReq) {
        return Question.builder()
                .text(questionReq.getText())
                .type(questionReq.getType())
                .isActive(questionReq.getIsActive())
                .build();
    }
}

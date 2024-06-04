package com.jeison.perfomance_test.infrastructure.helpers;

import com.jeison.perfomance_test.api.dto.request.SurveyReq;
import com.jeison.perfomance_test.api.dto.response.SurveyResp;
import com.jeison.perfomance_test.domain.entities.Survey;

public class SurveyHelper {

    public static SurveyResp surveyToResp(Survey survey) {
        return SurveyResp.builder()
                .id(survey.getId())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .timeStamp(survey.getTimeStamp())
                .isActive(survey.getIsActive())
                .build();
    }

    public static Survey reqToSurvey(SurveyReq surveyReq) {
        return Survey.builder()
                .title(surveyReq.getTitle())
                .description(surveyReq.getDescription())
                .isActive(surveyReq.getIsActive())
                .build();
    }
}

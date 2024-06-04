package com.jeison.perfomance_test.infrastructure.abstract_services;

import com.jeison.perfomance_test.api.dto.request.SurveyReq;
import com.jeison.perfomance_test.api.dto.response.SurveyResp;
import com.jeison.perfomance_test.api.dto.response.SurveyRespWithQuestions;

public interface ISurveyService extends CrudService<SurveyReq, SurveyResp, Long> {

    public final String FIELD_BY_SORT = "timeStamp";
    public SurveyRespWithQuestions findByIdWithQuestions(Long id);

}

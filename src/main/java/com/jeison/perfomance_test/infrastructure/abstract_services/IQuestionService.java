package com.jeison.perfomance_test.infrastructure.abstract_services;

import com.jeison.perfomance_test.api.dto.request.QuestionOnlyTextReq;
import com.jeison.perfomance_test.api.dto.request.QuestionReq;
import com.jeison.perfomance_test.api.dto.response.QuestionResp;

public interface IQuestionService extends CrudService<QuestionReq,QuestionResp,Long>{

    public final String FIELD_BY_SORT = "type";
    public QuestionResp updateText(QuestionOnlyTextReq questionOnlyTextReq, Long id);

}

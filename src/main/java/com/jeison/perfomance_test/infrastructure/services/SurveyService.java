package com.jeison.perfomance_test.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.perfomance_test.api.dto.request.SurveyReq;
import com.jeison.perfomance_test.api.dto.response.SurveyResp;
import com.jeison.perfomance_test.api.dto.response.SurveyRespWithQuestions;
import com.jeison.perfomance_test.domain.entities.Survey;
import com.jeison.perfomance_test.domain.repositories.SurveyRepository;
import com.jeison.perfomance_test.infrastructure.abstract_services.ISurveyService;
import com.jeison.perfomance_test.infrastructure.helpers.SurveyHelper;
import com.jeison.perfomance_test.utils.enums.SortType;
import com.jeison.perfomance_test.utils.exception.BadRequestException;
import com.jeison.perfomance_test.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService implements ISurveyService {

    // @Autowired
    // private final EmailHelper emailHelper;

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Page<SurveyResp> findAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
            default -> throw new IllegalArgumentException("No valid sort: " + sortType);
        }

        Pageable pageable = pageRequest;

        return surveyRepository.findAll(pageable).map(survey -> SurveyHelper.surveyToResp(survey));
    }

    public SurveyRespWithQuestions findByIdWithQuestions(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public SurveyResp findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public SurveyResp create(SurveyReq request) {
        
        // if (Objects.nonNull(client.getEmail())) {
        // this.emailHelper.sendMail(client.getEmail(), client.getFirstName(),
        // employee.getFirstName(), appointment.getDateTime());
        // }
        return SurveyHelper.surveyToResp(surveyRepository.save(SurveyHelper.reqToSurvey(request)));
    }

    @Override
    public SurveyResp update(SurveyReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Survey getById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("survey")));
    }

}

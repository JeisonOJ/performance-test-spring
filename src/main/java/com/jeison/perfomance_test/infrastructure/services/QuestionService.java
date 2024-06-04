package com.jeison.perfomance_test.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jeison.perfomance_test.api.dto.request.QuestionOnlyTextReq;
import com.jeison.perfomance_test.api.dto.request.QuestionReq;
import com.jeison.perfomance_test.api.dto.response.QuestionResp;
import com.jeison.perfomance_test.domain.entities.OptionQuestion;
import com.jeison.perfomance_test.domain.entities.Question;
import com.jeison.perfomance_test.domain.repositories.OptionQuestionRepository;
import com.jeison.perfomance_test.domain.repositories.QuestionRepository;
import com.jeison.perfomance_test.infrastructure.abstract_services.IQuestionService;
import com.jeison.perfomance_test.infrastructure.helpers.OptionQuestionHelper;
import com.jeison.perfomance_test.infrastructure.helpers.QuestionHelper;
import com.jeison.perfomance_test.utils.enums.SortType;
import com.jeison.perfomance_test.utils.enums.TypeQuestion;
import com.jeison.perfomance_test.utils.exception.BadRequestException;
import com.jeison.perfomance_test.utils.message.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionQuestionRepository optionQuestionRepository;

    @Override
    public Page<QuestionResp> findAll(int page, int size, SortType sortType) {
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

        return questionRepository.findAll(pageable).map(question -> QuestionHelper.questionToResp(question));
    }

    @Override
    public QuestionResp findById(Long id) {
        return QuestionHelper.questionToResp(getById(id));
    }

    @Override
    public QuestionResp create(QuestionReq request) {
        Question question = new Question();
        if (request.getType().equals(TypeQuestion.CLOSED)) {
            if (request.getOptions().isEmpty()) {
                throw new IllegalArgumentException("Options are required");
            }
            question = questionRepository.save(QuestionHelper.reqToQuestion(request));
            Question question2 = getById(question.getId());
            List<OptionQuestion> options = request.getOptions().stream().map(option -> {
                OptionQuestion optionQuestion = OptionQuestionHelper.reqToQuestion(option);
                optionQuestion.setQuestion(question2);
                return optionQuestionRepository.save(optionQuestion);
            }).toList();
            question.setOptions(options);
        }else if (request.getType().equals(TypeQuestion.OPEN)) {
            question = questionRepository.save(QuestionHelper.reqToQuestion(request));
        }else{
            throw new IllegalArgumentException("type must be OPEN or CLOSED");
        }
        
        return QuestionHelper.questionToResp(question);
    }

    @Override
    public QuestionResp update(QuestionReq request, Long id) {
        getById(id);
        Question question = new Question();
        if (request.getType().equals(TypeQuestion.CLOSED)) {
            if (request.getOptions().isEmpty()) {
                throw new IllegalArgumentException("Options are required");
            }
            Question questionToUpdate = QuestionHelper.reqToQuestion(request);
            questionToUpdate.setId(id);
            question = questionRepository.save(questionToUpdate);
            Question question2 = getById(question.getId());
            List<OptionQuestion> options = request.getOptions().stream().map(option -> {
                OptionQuestion optionQuestion = OptionQuestionHelper.reqToQuestion(option);
                optionQuestion.setQuestion(question2);
                return optionQuestionRepository.save(optionQuestion);
            }).toList();
            question.setOptions(options);
        }else if (request.getType().equals(TypeQuestion.OPEN)) {
            Question questionToUpdate = QuestionHelper.reqToQuestion(request);
            questionToUpdate.setId(id);
            question = questionRepository.save(questionToUpdate);
        }else{
            throw new IllegalArgumentException("type must be OPEN or CLOSED");
        }
        
        return QuestionHelper.questionToResp(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.delete(getById(id));
    }

    private Question getById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("question")));
    }

    @Override
    public QuestionResp updateText(QuestionOnlyTextReq questionOnlyTextReq, Long id) {
       Question question = getById(id);
       question.setText(questionOnlyTextReq.getText());
       return QuestionHelper.questionToResp(question);
    }

}

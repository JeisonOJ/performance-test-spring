package com.jeison.perfomance_test.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyRespWithQuestions {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime timeStamp;
    private Boolean isActive;
    private List<QuestionResp> questions;
}

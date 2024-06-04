package com.jeison.perfomance_test.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyResp {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime timeStamp;
    private Boolean isActive;

}

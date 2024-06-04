package com.jeison.perfomance_test.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResp {

    private Long id;
    private String userName;
    private String email;
    private Boolean isActive;

}

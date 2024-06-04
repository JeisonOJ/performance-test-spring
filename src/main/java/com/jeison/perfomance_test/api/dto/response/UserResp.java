package com.jeison.perfomance_test.api.dto.response;

import com.jeison.perfomance_test.utils.enums.RoleUser;

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
    private String fullname;
    private RoleUser roleUser;

}

package com.jeison.perfomance_test.infrastructure.helpers;

import com.jeison.perfomance_test.api.dto.request.UserReq;
import com.jeison.perfomance_test.api.dto.response.UserResp;
import com.jeison.perfomance_test.domain.entities.User;

public class UserHelper {

    public static UserResp userToResp(User user) {
        return UserResp.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .isActive(user.isActive())
                .build();
    }

    public static User reqToUser(UserReq userReq) {
        return User.builder()
                .userName(userReq.getUserName())
                .password(userReq.getPassword())
                .email(userReq.getEmail())
                .isActive(userReq.isActive())
                .build();
    }

}

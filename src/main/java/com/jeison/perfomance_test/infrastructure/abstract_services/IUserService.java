package com.jeison.perfomance_test.infrastructure.abstract_services;

import com.jeison.perfomance_test.api.dto.request.UserReq;
import com.jeison.perfomance_test.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq, UserResp, Long> {

}

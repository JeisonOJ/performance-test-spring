package com.jeison.perfomance_test.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jeison.perfomance_test.api.dto.request.UserReq;
import com.jeison.perfomance_test.api.dto.response.UserResp;
import com.jeison.perfomance_test.domain.entities.User;
import com.jeison.perfomance_test.domain.repositories.UserRepository;
import com.jeison.perfomance_test.infrastructure.abstract_services.IUserService;
import com.jeison.perfomance_test.infrastructure.helpers.UserHelper;
import com.jeison.perfomance_test.utils.enums.SortType;
import com.jeison.perfomance_test.utils.exception.BadRequestException;
import com.jeison.perfomance_test.utils.message.ErrorMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    // @Autowired
    // private final EmailHelper emailHelper;

    // va luego de agregar
    // if (Objects.nonNull(client.getEmail())) {
    //     this.emailHelper.sendMail(client.getEmail(), client.getFirstName(), employee.getFirstName(), appointment.getDateTime());
    // }

    @Override
    public Page<UserResp> findAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public UserResp findById(Long id) {
        return UserHelper.userToResp(getById(id));
    }

    @Override
    public UserResp create(UserReq request) {
        return UserHelper.userToResp(userRepository.save(UserHelper.reqToUser(request)));
    }

    @Override
    public UserResp update(UserReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }

}

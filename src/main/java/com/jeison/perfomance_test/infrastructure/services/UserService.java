package com.jeison.perfomance_test.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<UserResp> findAll(int page, int size, SortType sortType) {
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
        return userRepository.findAll(pageable).map(user -> UserHelper.userToResp(user));
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
        getById(id);
        User user = UserHelper.reqToUser(request);
        user.setId(id);
        return UserHelper.userToResp(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(getById(id));

    }

    private User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("user")));
    }

}

package com.jeison.perfomance_test.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.jeison.perfomance_test.utils.enums.SortType;

public interface CrudService<RQ, RS, ID> {

    public Page<RS> findAll(int page, int size, SortType sortType);

    public RS findById(ID id);

    public RS create(RQ request);

    public RS update(RQ request, ID id);

    public void delete(ID id);
}

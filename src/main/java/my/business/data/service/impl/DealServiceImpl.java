package my.business.data.service.impl;

import my.business.data.entities.Deal;
import my.business.data.repo.DealRepository;
import my.business.data.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {
    private DealRepository dealRepository;

    public DealServiceImpl() {
    }

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public void addUser(Deal deal) {
        dealRepository.saveAndFlush(deal);
    }
}

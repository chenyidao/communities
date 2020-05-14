package com.community.cyd.service;

import com.community.cyd.mapper.PaymentMapper;
import com.community.cyd.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper payMapper;

    public void insertRecord(Payment pay) {
        payMapper.insert(pay);
    }
}

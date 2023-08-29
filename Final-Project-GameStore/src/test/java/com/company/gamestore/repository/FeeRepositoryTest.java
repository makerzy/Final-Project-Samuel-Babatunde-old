package com.company.gamestore.repository;


import com.company.gamestore.model.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FeeRepositoryTest {

    @Autowired
    FeeRepository feeRepository;

    @Test
    public void shouldGetFeeByProductType(){
        Fee fee = new Fee();
        fee.setFee(13.99);
        fee.setProductType("Console");

        feeRepository.save(fee);
        List<Fee> fees = feeRepository.findByProductType("Console");
        assertTrue(fees.contains(fee));

    }
}

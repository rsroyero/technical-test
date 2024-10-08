package org.example.service;

import org.example.repository.RetirementFundRepository;
import org.example.entity.RetirementFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetirementFundService {

    @Autowired
    private RetirementFundRepository rRepository;


    public List<RetirementFund> getAllFunds() {
        return rRepository.getAllFunds();
    }
}

package org.example.controller;

import org.example.entity.RetirementFund;
import org.example.service.RetirementFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class RetirementFundControl {

        @Autowired
        private RetirementFundService retirementFundService;

        @GetMapping(produces = "application/json")
        public ResponseEntity<List<RetirementFund>> getAllCourses() {
            List<RetirementFund> accounts = retirementFundService.getAllFunds();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
}

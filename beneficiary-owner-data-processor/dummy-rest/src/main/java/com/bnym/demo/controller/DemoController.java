package com.bnym.demo.controller;

import com.bnym.demo.model.LoanAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class DemoController {

    @RequestMapping("/all")
    @GetMapping(produces = "application/json")
    public ResponseEntity<LoanAccount[]> show() {
        return ResponseEntity.ok(
                new LoanAccount[]{
                        new LoanAccount(
                                12131,
                                "l1010",
                                10000,
                                50000,
                                "5-Aug-10",
                                "4-Aug-20"),
                        new LoanAccount(
                                12131,
                                "a1010",
                                1000,
                                5000,
                                "5-Aug-10",
                                "4-Aug-20"),
                        new LoanAccount(
                                19634,
                                "m2020",
                                20000,
                                100000,
                                "5-Aug-10",
                                "4-Aug-20"),
                        new LoanAccount(
                                32454,
                                "n3030",
                                30000,
                                150000,
                                "5-Aug-10",
                                "4-Aug-20"),
                        new LoanAccount(
                                65436,
                                "o4040",
                                40000,
                                20000,
                                "5-Aug-10",
                                "4-Aug-20")
                });
    }

}

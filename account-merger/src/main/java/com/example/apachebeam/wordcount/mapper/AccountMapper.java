package com.example.apachebeam.wordcount.mapper;

import com.example.apachebeam.wordcount.model.Account;

public class AccountMapper {

    public static Account buildAccountFromString(String[] split) {
        return new Account(
                Integer.parseInt(split[0]),
                split[1],
                split[2],
                Integer.parseInt(split[3]),
                split[4],
                split[5],
                split[6],
                split[7],
                split[8]
        );
    }
}

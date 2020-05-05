package com.bnym.beneficiary.owner.data.processor.mapper;

import com.bnym.beneficiary.owner.data.processor.model.Account;
import com.bnym.beneficiary.owner.data.processor.model.BeneficiaryOwner;
import com.bnym.beneficiary.owner.data.processor.model.Position;
import com.bnym.beneficiary.owner.data.processor.model.Result1;
import com.bnym.beneficiary.owner.data.processor.model.Result1Map;
import com.bnym.beneficiary.owner.data.processor.model.Result2;

import java.util.List;
import java.util.Map;

public class PojoMapper {

    public static Account buildAccount(String[] split) {
        return new Account(
                split[0],
                Long.parseLong(split[1]),
                split[2],
                split[3]);
    }

    public static Result1 buildResult1(Account account, Position position) {
        Result1 result1 = new Result1();
        result1.setIdOwner(account.getIdOwner());
        result1.setAccountStatus(account.getAccountStatus());
        result1.setCountry(account.getCountry());
        result1.setAssetType(position.getAssetType());
        result1.setAccountNo(position.getAccountNo());
        result1.setQuantity(position.getQuantity());
        result1.setExchange(position.getExchange());
        result1.setIsin(position.getIsin());
        result1.setUnrealizedGain(position.getUnrealizedGain());
        return result1;
    }

    public static Result1Map buildResult1Map(Account account, Map<Long, Map<String, List<Position>>> map) {
        return new Result1Map(
                account.getIdOwner(),
                account.getAccountNo(),
                account.getAccountStatus(),
                account.getCountry(),
                map.get(account.getAccountNo()));
    }

    public static BeneficiaryOwner buildBeneficiaryOwner(String[] split) {
        return new BeneficiaryOwner(
                split[0],
                split[1],
                split[2],
                split[5]);
    }

    public static Result2 buildResult2(Result1 result1, BeneficiaryOwner beneficiaryOwner) {
        Result2 result2 = new Result2();
        result2.setAssetType(result1.getAssetType());
        result2.setQuantity(result1.getQuantity());
        result2.setExchange(result1.getExchange());
        result2.setIsin(result1.getIsin());
        result2.setUnrealizedGain(result1.getUnrealizedGain());
        result2.setAccountNo(result1.getAccountNo());
        result2.setIdOwner(beneficiaryOwner.getIdOwner());
        result2.setBoName(beneficiaryOwner.getBoName());
        result2.setResidentCountry(beneficiaryOwner.getResidentCountry());
        return result2;
    }
}

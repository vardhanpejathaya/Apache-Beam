package com.bnym.beneficiary.owner.data.processor.function;

import com.bnym.beneficiary.owner.data.processor.helper.RESTHelper;
import com.bnym.beneficiary.owner.data.processor.mapper.PojoMapper;
import com.bnym.beneficiary.owner.data.processor.model.Account;
import com.bnym.beneficiary.owner.data.processor.model.BeneficiaryOwner;
import com.bnym.beneficiary.owner.data.processor.model.Position;
import com.bnym.beneficiary.owner.data.processor.model.Result1;
import com.bnym.beneficiary.owner.data.processor.model.Result2;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TupleTag;

import java.util.List;
import java.util.Map;

public class Function {

    public static SimpleFunction<Position, KV<Long, Position>> generateKVFromPositionFn() {
        return new SimpleFunction<Position, KV<Long, Position>>() {
            @Override
            public KV<Long, Position> apply(Position position) {
                return KV.of(position.getAccountNo(), position);
            }
        };
    }

    public static SimpleFunction<String, KV<Long, Account>> generateKVFromAccountFn() {
        return new SimpleFunction<String, KV<Long, Account>>() {
            @Override
            public KV<Long, Account> apply(String accountData) {
                String[] split = accountData.split(",");
                return KV.of(Long.parseLong(split[1]), PojoMapper.buildAccount(split));
            }
        };
    }

    public static SimpleFunction<String, KV<String, BeneficiaryOwner>> generateKVFromBeneficiaryOwnerFn() {
        return new SimpleFunction<String, KV<String, BeneficiaryOwner>>() {
            @Override
            public KV<String, BeneficiaryOwner> apply(String beneficiaryOwnerData) {
                String[] split = beneficiaryOwnerData.split(",");
                return KV.of(split[0], PojoMapper.buildBeneficiaryOwner(split));
            }
        };
    }

    public static SimpleFunction<Result1, KV<String, Result1>> generateKVFromResult1Fn() {
        return new SimpleFunction<Result1, KV<String, Result1>>() {
            @Override
            public KV<String, Result1> apply(Result1 result1) {
                return KV.of(result1.getIdOwner(), result1);
            }
        };
    }

    public static DoFn<KV<Long, CoGbkResult>, Result1> extractResult1Fn(
            TupleTag<Position> positionTag,
            TupleTag<Account> accountDataTag,
            Map<Long, Map<String, List<Position>>> map) {
        return new DoFn<KV<Long, CoGbkResult>, Result1>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                KV<Long, CoGbkResult> element = c.element();
                element.getValue().getAll(accountDataTag).forEach(account -> {
                    RESTHelper.postResult(PojoMapper.buildResult1Map(account, map));
                    element.getValue()
                            .getAll(positionTag)
                            .forEach(position -> c.output(PojoMapper.buildResult1(account, position)));
                });
            }
        };
    }

    public static DoFn<KV<String, CoGbkResult>, Result2> extractResult2Fn(
            TupleTag<Result1> result1Tag,
            TupleTag<BeneficiaryOwner> beneficiaryOwnerTag) {
        return new DoFn<KV<String, CoGbkResult>, Result2>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                KV<String, CoGbkResult> element = c.element();
                element.getValue().getAll(result1Tag).forEach(result1 -> {
                    element.getValue()
                            .getAll(beneficiaryOwnerTag)
                            .forEach(beneficiaryOwner -> c.output(
                                    PojoMapper.buildResult2(result1, beneficiaryOwner)));
                });
            }
        };
    }
}

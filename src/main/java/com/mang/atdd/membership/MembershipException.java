package com.mang.atdd.membership;

import lombok.Getter;

@Getter
public class MembershipException extends RuntimeException {

    private final MembershipErrorResult errorResult;

    public MembershipException(String message, MembershipErrorResult errorResult) {
        super(message);
        this.errorResult = errorResult;
    }

    public MembershipException(MembershipErrorResult errorResult) {
        this.errorResult = errorResult;
    }


    public MembershipErrorResult getErrorResult() {
        return errorResult;
    }
}

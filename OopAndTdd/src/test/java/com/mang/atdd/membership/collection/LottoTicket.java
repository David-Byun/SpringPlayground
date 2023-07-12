package com.mang.atdd.membership.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LottoTicket {

    // final은 재할당 금지 역할만 하기 때문에 일급 컬렉션과 래퍼 클래스로 해결
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Long> lottoNumber;

    public LottoTicket(List<Long> lottoNumber) {
        validateSize(lottoNumber);
        validateDuplicate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateSize(List<Long> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 6개만 가능");
        }
    }

    private void validateDuplicate(List<Long> lottoNumbers) {
        Set<Long> nonDuplicateNumbers = new HashSet<>(lottoNumbers);
    }
    
}

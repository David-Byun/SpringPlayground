package com.mang.atdd.membership.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    /*
        final로 선언한 후 생성자에게 값을 검증하고 초기화
        한번 객체가 생성되면 변경 불가
     */
    private final List<Player> players;

    public Players(List<Player> players) {
        checkDulicateName(players);
        this.players = players;
    }

    private void checkDulicateName(List<Player> players) {
        Set<Player> nameSet = new HashSet<>(players);
        if (nameSet.size() != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }
}

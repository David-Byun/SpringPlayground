package com.mang.atdd.oop.shop;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="OPTION_GROUP_SPECS")
@Getter
public class OptionGroupSpecification {

    @Id
    @GeneratedValue
    @Column(name="OPTION_GROUP_SPEC_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="EXCLUSIVE")
    private boolean exclusive;

    @Column(name="BASIC")
    private boolean basic;

    //new ArrayList<> : 빈 Array로 초기화, 없으면 null로 초기화
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="OPTION_GROUP_SPEC_ID")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    //list형 전체 constructor일 경우에는 list로 포함하지만, 일부 constructor 로 진행시에는 리스트 내용을 꺼내서 집어놓음
    @Builder
    public OptionGroupSpecification(Long id, String name, boolean exclusive, boolean basic, List<OptionSpecification> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(options);
    }

    public OptionGroupSpecification(String name, boolean exclusive, boolean basic, OptionSpecification ... options) {
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        Arrays.asList(options);
    }

    /*
        option 그룹 name을 비교하고 그 안의 option들을 비교한다.
        validation을 위한 조건들 클래스 안에 포함
     */
    public boolean isSatisfied(String groupName, List<Option> satisfied) {
        if (!name.equals(groupName)) {
            return false;
        }

        if (satisfied.isEmpty()) {
            return false;
        }

        if (exclusive && satisfied.size() > 1) {
            return false;
        }

        return true;
    }

}

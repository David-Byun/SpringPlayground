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
public class Menu {
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="OPTION_GROUP_SPEC_ID")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public Menu(Long id, String name, boolean exclusive, boolean basic, List<OptionSpecification> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(options);
    }

    public Menu(String name, boolean exclusive, boolean basic, List<OptionSpecification> options) {
        this(null, name, exclusive, basic, options);
    }
}

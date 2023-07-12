package com.mang.atdd.oop.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_option_group")
public class OrderOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

}
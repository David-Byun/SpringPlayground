package com.mang.atdd.oop.order;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_LINE_ITEMS")
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LINE_ITEM_ID")
    private Long id;

    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "FOOD_NAME")
    private String name;

    @Column(name = "FOOD_COUNT")
    private int count;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="ORDER_LINE_ITEM_ID")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    public OrderLineItem(Long menuId, String name, int count, List<OrderOptionGroup> groups) {
        this(null, menuId, name, count, groups);
    }

    public OrderLineItem() {
    }

    @Builder
    public OrderLineItem(Long id, Long menuId, String name, int count, List<OrderOptionGroup> groups) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups = groups;
    }
}

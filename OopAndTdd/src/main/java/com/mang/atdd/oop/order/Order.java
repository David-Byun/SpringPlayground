package com.mang.atdd.oop.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name="ORDERS")
public class Order {
    public enum OrderStatus {
        ORDERED, PAYED, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_ID")
    private Long id;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name="SHOP_ID")
    private Long shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name="ORDERED_TIME")
    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private OrderStatus orderStatus;

    public Order(Long userId, Long shopId, List<OrderLineItem> items) {
        this(null, userId, shopId, items, LocalDateTime.now(), null);
    }

    @Builder
    public Order(Long id, Long userId, Long shopId, List<OrderLineItem> items, LocalDateTime orderedTime, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.orderLineItems.addAll(items);
    }

    private void ordered() {
        this.orderStatus = OrderStatus.ORDERED;
    }

    private void payed() {
        this.orderStatus = OrderStatus.PAYED;
    }

    private void delivered() {
        this.orderStatus = OrderStatus.DELIVERED;
    }



}

package org.hdcd.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pay_coin_history")
public class PayCoinHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNo;

    private Long userNo;

    private Long itemId;

    @Transient
    //영속성 제외
    private String itemName;

    private int amount;

    private LocalDateTime regDate;

    private LocalDateTime upDate;
}

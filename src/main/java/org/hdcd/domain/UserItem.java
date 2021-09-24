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
@Table(name = "user_item")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userItemNo;

    private Long userNo;

    private Long itemId;


    @Transient
    private String itemName;
    @Transient
    private Integer price;
    @Transient
    private String description;
    @Transient
    private String pictureUrl;

    private LocalDateTime regDate;

    private LocalDateTime upDate;
}

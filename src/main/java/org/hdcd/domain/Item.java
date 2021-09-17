package org.hdcd.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(length = 50)
    private String itemName;

    private int price;

    @Column(length = 250)
    private String description;

    @Column(length = 200)
    private String pictureUrl;

    @Transient
    private MultipartFile picture;

    @Column(length = 200)
    private String previewUrl;

    @Transient
    private MultipartFile preview;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime upDate;
}

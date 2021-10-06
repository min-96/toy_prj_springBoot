package org.hdcd.common.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name ="login_log")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNo;

    private Long userNo;

    @Column(length = 50,nullable = false)
    private String userId;

    @Column(length = 50, nullable = false)
    private String remoteAddr;

    @CreationTimestamp
    public LocalDateTime regDate;

    @UpdateTimestamp
    public LocalDateTime upDate;
}

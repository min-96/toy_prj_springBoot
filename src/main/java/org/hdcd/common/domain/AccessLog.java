package org.hdcd.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logNo;

    @Column(length = 200,nullable = false)
    private String requestUri;
    @Column(length = 100,nullable = false)
    private String className;
    @Column(length = 50,nullable = false)
    private String classSimpleName;
    @Column(length = 100,nullable = false)
    private String methodName;
    @Column(length = 50,nullable = false)
    private String remoteAddr;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime upDate;
}

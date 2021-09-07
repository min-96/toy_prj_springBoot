package org.hdcd.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Setter
@ToString
@EqualsAndHashCode(of = "groupCode")
@Table(name = "code_group")
public class CodeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 3)
    private String groupCode;

    @Column(length = 30, nullable = false)
    private String groupName;
    @Column(length = 1)
    private String useYn="Y";
    @CreationTimestamp
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime upDate;


}

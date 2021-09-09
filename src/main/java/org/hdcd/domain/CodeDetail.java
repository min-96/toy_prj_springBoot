package org.hdcd.domain;


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
@IdClass(CodeDetailId.class)
@Table(name = "code_detail")
public class CodeDetail {

    @Id
    @Column(length = 3)
    private String groupCode;

    @Id
    @Column(length = 3)
    private String codeValue;

    @Column(length = 30)
    private String codeName;

    private int sortSeq;
    @Column(length = 1)
    private String useYn ="Y";
    @CreationTimestamp
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime upDate;

}

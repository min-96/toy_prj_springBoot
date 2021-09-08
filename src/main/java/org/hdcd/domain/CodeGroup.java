package org.hdcd.domain;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Column(length = 3)
//    @NotBlank
//    @Size(max = 3)
    private String groupCode;

    @Column(length = 30, nullable = false)
//    @NotBlank
//    @Size(max = 30)
    private String groupName;


    @Column(length = 1)
    private String useYn="Y";


    @CreationTimestamp
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime upDate;


}

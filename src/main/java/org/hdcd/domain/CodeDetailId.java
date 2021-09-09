package org.hdcd.domain;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//직렬화
public class CodeDetailId implements Serializable {

    private static final long serialVersionUID = 1L;


    private String groupCode;

    private  String codeValue;
}

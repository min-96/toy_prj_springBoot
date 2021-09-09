package org.hdcd.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member_auth")
public class MemberAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthNo;

    @Column(name="user_no")
    private Long userNo;

    @Column(length = 50)
    private String auth;



}

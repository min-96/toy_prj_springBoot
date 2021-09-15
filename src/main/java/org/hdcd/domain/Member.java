package org.hdcd.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name="memebr")
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @NotBlank
    @Column(length = 50)
    private String userId;

    @NotBlank
    @Column(length = 200)
    private String userPwd;

    @NotBlank
    @Column(length = 100)
    private String userName;

    @Column(length = 3,nullable = false)
    private String family;

    private int coin;
    @CreationTimestamp
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime upDate;




        //fetch 멤버조회할때 MemberAuth도 조회
    //cascade 엔티티 영속성 변화될때 모든게 변화됨
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name="user_no")
    private List<MemberAuth> authList = new ArrayList<>();


    public void addAuth(MemberAuth auth){
        authList.add(auth);
    }


}

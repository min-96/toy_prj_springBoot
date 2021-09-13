package org.hdcd.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
//ㅈㅏ동 로그인 정보 저장 클래스 정의
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(length = 64)
    private String username;
    @Column(length = 64)
    private String token;

    @CreationTimestamp
    @Column(name = "last_used")
    private Timestamp lastUesd;
}

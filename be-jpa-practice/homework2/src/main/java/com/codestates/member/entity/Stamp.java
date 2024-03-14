package com.codestates.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stampId;

    @Column(nullable = false)
    private int stampCount;

    //일단 member를 통해 회원의 stampCount
    @OneToOne(mappedBy = "stamp")
    private Member member;


    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public void setStampCount(int stampCount) {
        this.stampCount = stampCount;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}

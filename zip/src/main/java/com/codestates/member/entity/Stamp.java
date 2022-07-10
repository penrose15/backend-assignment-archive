package com.codestates.member.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stampId;

    @Column(nullable = false)
    private Integer stampCount;



    @OneToOne(mappedBy = "stamp")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if(member.getStamp() != this) {
            member.setStamp(this);
            member.getStamp().setStampCount(0);
        }
    }


}

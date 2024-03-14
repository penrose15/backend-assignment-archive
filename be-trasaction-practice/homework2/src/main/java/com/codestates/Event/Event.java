package com.codestates.Event;

import com.codestates.helper.EmailSender;
import com.codestates.member.entity.Member;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class Event{
    private Member member;


    public Event(Member member) {

        this.member = member;

    }

    public Member getMember() {
        return member;
    }

}

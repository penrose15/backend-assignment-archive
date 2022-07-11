package com.codestates.Event;

import com.codestates.helper.EmailSender;
import com.codestates.helper.email.EmailSendable;
import com.codestates.helper.email.MockEmailSendable;
import com.codestates.helper.email.MockExceptionEmailSendable;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final MemberService memberService;

    private final EmailSender emailSender;


    @EventListener
    public void create(Event e) throws InterruptedException {


        try {

            emailSender.sendEmail("any email message");

        }catch (Exception ex) {
            Member member = e.getMember();

            memberService.deleteMember(member.getMemberId());

        }





    }

}

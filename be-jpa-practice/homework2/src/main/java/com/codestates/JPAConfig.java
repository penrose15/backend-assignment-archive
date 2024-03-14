/*
package com.codestates;

import com.codestates.member.entity.Member;
import com.codestates.member.entity.Stamp;
import com.codestates.order.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

@Profile("fjym")
@Configuration
public class JPAConfig {
    private EntityManager em;
    private EntityTransaction tx;

    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            Member member = new Member("hgd@gmail.com", "Hong Gil Dong",
                    "010-1111-1111");
            Order order = new Order();
            member.addOrder(order);
            order.addMember(member);

            System.out.println("#stamp and member persist===============================================");

            Stamp stamp = new Stamp();
            stamp.setStampCount(1);
            stamp.setModifiedAt(LocalDateTime.now());
            member.addStamp(stamp);

            em.persist(order);
            em.persist(member);
            em.persist(stamp);

            tx.commit();

        };
    }

}
*/

package eek.granular;

import eek.granular.entity.Ji;
import eek.granular.entity.JiYoung;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {

    private EntityManager em;
    //JPA의 영속성 컨텍스트는 EntityManager 클래스에 의해 관리된다
    private EntityTransaction tx;

    @Bean
    @Order(1)
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        //EntityManager클래스 객체는 EntityManagerFactory객체를 Spring으로부터 DI받을 수 있다.
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();//JPA에서는 이 Transaction 객체를 기준으로 데이터베이스의 테이블에 데이터를 저장한다.
            em.persist(new JiYoung("ddong@gmail.com", 20));
            em.persist(new JiYoung("ji@naver.com", 22));
            //em.persist() 메서드 호출하면 영속성 컨텍스트에 member 객체의 정보들이 저장된다.
            JiYoung ji = em.find(JiYoung.class,1L);
            JiYoung ji1 = em.find(JiYoung.class, 2L);
            System.out.println("Email: "+ji.getEmail()+", Age: "+ji.getAge());
            System.out.println("Email: "+ji1.getEmail()+", Age: "+ji1.getAge());
            ji.setAge(21);
            tx.commit(); // .commit()를 호출하는 시점에서 영속성 컨텍스트에 저장되어 있는 member객체를 데이터베이스의 테이블에 저장한다.

            tx.begin();
            JiYoung ji2 = em.find(JiYoung.class, 1L);
            em.remove(ji2);
            tx.commit();
            //em.persist() -> 1차 캐시에 엔티티 클래스의 객체가 저장되고 쓰기 지연 SQL저장소에 INSERT 쿼리가 등록된다
            //tx.commit() -> 을 하는 순간 지연 SQL 저장소에 등록된 INSERT쿼리가 실행된다
            //em.find() -> 1차 캐시에서 해당 객체가 있는지 조회하고 테이블에 SELECT쿼리를 전송해서 조회한다,

            //tx.commit() 메서드에는 em.flush()가 호출되어 영속성 컨텍스트의 변경 내용을
            //데이터 베이스에 반영한다.

        };
    }
    @Bean
    @Order(2)
    public CommandLineRunner GeneratedValueTest(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            em.persist(new Ji("전지영"));
            em.persist(new Ji("바보"));
            Ji ji = em.find(Ji.class, 1L);
            Ji ji1 = em.find(Ji.class, 2L);
            System.out.println("#ID: "+ji1.getJiId()+","+ji.getJiId());
            System.out.println("#createdAt: "+ji.getCreatedAt());
            tx.commit();

        };
    }

}

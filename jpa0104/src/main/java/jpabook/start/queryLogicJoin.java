package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class queryLogicJoin {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        String jpql = "select m from MemberFive m join m.team t where " +
                      "t.name=:teamName";

        List<MemberFive> resultList = em.createQuery(jpql, MemberFive.class).setParameter("teamName", "팀1").getResultList();
        
        for( MemberFive memberFive : resultList ){
            System.out.println("memberFive.getUsername() = " + memberFive.getUsername());
        }

    }

}
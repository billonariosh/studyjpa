package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class testSaveMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        MemberFive member1 = new MemberFive("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        MemberFive member2 = new MemberFive("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);

        /*
         * MemberFive의 TEAM 객체에 TEAM_ID를 다대일(ManyToOne)로 연결하여
         * memberFive의 member1을 조회 후 Team객체에 넣는게 가능해짐
         * MemberFive와 Team이 연관되어 있기에 아래 코드가 가능해짐.
         * */
//        MemberFive memberFive = em.find(MemberFive.class, "member1");
//        Team team = memberFive.getTeam();
//        System.out.println("team = " + team.getName());

        /*
        * 쿼리형태로 사용할 수 도있다. hibernate 형식으로 사용가능하다. 통계 쿼리 같이 복잡할때 사용한다.
        * */
        String jpql = "select m from MemberFive m join m.team t where " +
                "t.name=:teamName";

        List<MemberFive> resultList = em.createQuery(jpql, MemberFive.class).setParameter("teamName", "팀1").getResultList();

        for( MemberFive memberFive1 : resultList ){
            System.out.println("memberFive.getUsername() = " + memberFive1.getUsername());
        }

        em.flush();

        tx.commit();


    }
}
package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ExamMergeMain {
    /*
    비영속: 영속성 컨텍스트와 전혀 관계가 없는 상태
    영속: 영속성 컨텍스트에 저장된 상태
    준영속: 영속성 컨텍스트에 저장되었다가 분리된 상태
    삭제: 삭제된 상태

    영속성 컨텍스트는 어플리케이션과 데이터베이스 사이에서 개체를 보관하는 가상의 저장공간으로 생각하면 됨.

    영속성 컨텍스트에 보관한 데이터를 데이터베이스에 저장하려면 flush()명령어를 활용하거나, commit() 명령어를 이용하면됨.
    commit()명령어만 입력할 경우 자동으로 flush()명령어도 같이 실행 됨.
    보통은 test할경우에 flush()명령어를 별도로 사용함.

    준영속성 컨텍스트는 1차 캐시, 동일성 보장, 트랜잭션을 지원하는 쓰기 지연, 변경 감지, 지연 로딩 같은 기능을 사용할 수 없다.
    그러므로 persist()명령어나 merge()명령어를 잘 활용 해야 예외를 방지할 수 있다.
    */

    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String args[]){
        Member member = createMember("memberA", "회원1");

        /*
        회원명을 변경하려고 했으나, 영속성 컨텍스트가 존재하지 않는다.
        createMember 함수에서 close()를 실행하여 준영속성 상태를 만들었기 때문에.
        그렇기 때문에 데이터베이스에 반영할 수 없게된다.
        이때 준영속성 상태를 다시 영속성 상태로 변경하기 위해서는 merge()명령어를 사용해 상태를 변경해줘야한다.
        */
        member.setUsername("회원명변경");

        mergeMember(member);
    }

    static Member createMember(String id, String username){
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();

        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        //여기까지는 비영속성 상태!

        em1.persist(member); //persist를 통해서 영속성 상태로 만듦

        tx1.commit();

        em1.close(); //Entity를 준영속 상태를 만듦

        return member;
    }

    static void mergeMember(Member member){
        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        /*
        merge() 명령어로 준영속성 상태에서 영속성 상태로 변경된다.
         */
        Member mergeMember = em2.merge(member);
        tx2.commit();

        System.out.println("member.getUsername() = " + member.getUsername());

        System.out.println("mergeMember.getUsername() = " + mergeMember.getUsername());

        //현위치에서 member를 merge하여 새로운 Member개체에 넣어준다.
        //매개변수로 넘어온 Member개체는 준영속
        //merge()가 완료된 Member개체는 영속
        //그렇기에 false 가 나오게 됨.
        System.out.println("em2.contains(member) = " + em2.contains(member));
        //영속상태가 되어 true가 나오게 됨.
        System.out.println("em2.contains(mergeMember) = " + em2.contains(mergeMember));

        em2.close();

    }
}
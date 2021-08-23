package jpabook.start;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MemberFive {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    /*
    * 만약 다대다일경우
    * ManyToOne 과 OneToMany로 설정해주어 양방향처럼 처리되겠금 해줘야한다.
    * 단방향 + 단방향으로 설정하여 양방향처럼!
    * 이때 양방향 연관관계로 설정하면 객체의 참조는 둘인데 외래 키는 하나이다, 따라서 둘 사이에 차이가 발생한다.
    * 두 객체 연관관계 중 하나를 정해서 테이블의 외래키를 관리해야 하는데 이것을 *연관관계의 주인* 이라 한다.
    * */
    /*
     * 연관관계의 주인
     * 연관관계의 주인만이 데이터베이스 연관관계와 매핑되고 외래 키를 관리(수정, 삭제, 등록) 할 수 있다.
     * 주인인 아닌 쪽은 읽기만 가능하다.
     * 주인은 mappedBy속성을 사용하지 않는다.
     * 주인이 아니면 mappedBy속성을 사용해서 속성의 값으로 연관관계의 주인을 지정해야한다.
     * *** 중요! 데이터베이스 테이블의 다대일, 일대다 관계에서는 항상 다 쪽이 외래 키를 가진다.
     * ManyToOne쪽에는 mappedBy 속성을 쓸수 없다!  OneToMany쪽에서만 사용이 가능!
     *
     * 그런데!! 더 중요한 사실은 모두 객체 관점에서 양쪽 방향에 모두 값을 입력해주는 것이 가장 안전하다.
     * 그러므로 모두 값을 입력해줘야 정상적으로 입력처리가 된다. p.189
     * 결론! 객체의 양방향 연관관계는 양쪽 모두 관계를 맺어주자!
     */

    @ManyToOne // 다대일 구조라는 의미. MemberFive가 여러개, Team객체가 일인 구조.
    @JoinColumn(name="TEAM_ID") // 외래키를 매핑할때 사용함.
    private Team team;

    public MemberFive(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public MemberFive() {

    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }
}
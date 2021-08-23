package jpabook.start;

import javax.persistence.*;  //**
import java.util.Date;

/*
* 기타 사항으로 hibernate.hbm2ddl.auto 옵션은 운영 서버에서 사용하면 안된다.
* 자칫하다간 테이블이 삭제되면 어마어마한 일이 일어나기 때문이다.
* 그렇기 때문에 아래 어노테이션의 데이터베이스의 옵션을 설정하는 어노테이션들은
* 필요가 없을 수도 있지만, 개발자가 엔티티만 보고 다양한 제약조건을 파악 할 수 있다.
* */

@Entity
@Table(name="member", uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME","AGE"})})
public class Member {
    @Id
    //@GeneratedValue 기본값은 AUTO AUTO로 둘경우 데이터베이스의 종류에 따라 자동으로 설정해준다. MYSQL = IDENTITY기능이다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name="ID")
    private String id;

    //null이 불가능하다의 의미로 false > not null과 같음
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    //enum을 사용하려면 @Enumerated 어노테이션을 설정해 줘야 사용 가능!
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //날짜 타입 설정할때는 @Temporal
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*
    varchar타입 길이 제한이 없을 경우 varchar를 사용할 수 없게되어
    clob타입을 사용 해야 함. clob, blob타입으로 맵핑할 경우 @Lob을 사용
    MYSQL은 longtext 타입으로 설정함.!!
     */
    @Lob
    private String description;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
package entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/*
공통 필드가 존재할때 사용하는 어노테이션
예) 누가 생성했는지, 생성날짜, 누가 수정했는지 같이 모든 테이블에 공통된 필드가 존재
    createBy, createdDate, lastModifiedBy, lastModifiedDate
    상속받아서 하고 싶으면 사용할 수 있음.
    extends BaseEntity 추가
    추상클레스로 만드는게 좋음.
    테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑정보를 모으는 역할

 */
@MappedSuperclass
public abstract class BaseEntity {

    private Date createdDate;       //등록일
    private Date lastModifiedDate;  //수정일

    //Getter, Setter
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

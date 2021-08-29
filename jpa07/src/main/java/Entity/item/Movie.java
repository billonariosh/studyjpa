package entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// 구분자 값을 설정한다. item 테이블에 DTYPE 필드에 M 값으로 구분자 값으로 들어감.
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}

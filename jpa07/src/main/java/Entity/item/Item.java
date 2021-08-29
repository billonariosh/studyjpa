package entity.item;


import entity.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

/*
InheritanceType.SINGLE_TABLE > 단일 테이블로 구성
장점 속도가 빠르다. 1개의 테이블에서만 찾기떄문에.. , 쿼리가 단순
단점 정규화가 안되어있다. 자식 필드 들은 null을 허용해 줘야한다.(무결성 측면으로 봤을땐 안좋음)
    모든것을 저장하므로 성능이 느려질 수 있다.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
*/

/*
InheritanceType.TABLE_PER_CLASS > ITEM 테이블의 필드를 Album, Book, Movie 테이블에 나누어 각각 만듦.
단점은 Item 개체를 조회하게 되면, unionAll로 세가지 테이블을 모두 긁어서 찾아온다. 그렇기 때문에 비효율적으로 조회를 해온다.
안좋은 전략이다. 안쓰는게 좋음.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
*/

/*
InheritanceType.JOINED > 정규화 된 방식으로 구성
장점 정규화가 잘 되어있다.
단점 조회 시 조인을 하기때문에 속도문제가 있을 수 있다. 그러나 데이터베이스는 정규화가 기본이다.
*/
@Inheritance(strategy = InheritanceType.JOINED)
//구분자 값으로 생각하면 됨, extend되어 있는 class명을 default로 value로 가져간다.
//name = "DTYPE"은 필드명 설정
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;        //이름
    private int price;          //가격
    private int stockQuantity;  //재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    //Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

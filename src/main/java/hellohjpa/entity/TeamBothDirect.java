package hellohjpa.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity(name = "TB_TEAMBOTHDIRECT")
public class TeamBothDirect {

    // 양방향

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "TEAM_NAME")
    private String name;
    /*
     *   1. DB 설계 할 때
     *   일단 기본적으로 단방향으로 DB를 설계하고
     *   필요할 때만 양방향으로 DB를 설계하자
     *   2. 연관 관계 주인 설정 할 때는 FK를 가지고 있는 객체를 주인으로 하자
     *
     * */
    @OneToMany(mappedBy = "team")
    private List<MemberBothDirect> members = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MemberBothDirect> getMembers() {
        return members;
    }

    public void setMembers(List<MemberBothDirect> members) {
        this.members = members;
    }

}

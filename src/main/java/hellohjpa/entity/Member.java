package hellohjpa.entity;

import javax.persistence.*;

@Entity(name = "TB_MEMBER")
public class Member {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "USER_NAME", length = 20)
    private String name;

    @Column(name = "MEMBER_TYPE", length = 10)
    /*
     *   추천 : EnumType.STRING
     *   이유 : default가 ORDINAL인데 이 값은 Enum Class에 선언 순서가 변경되면 값이 꼬일 수 있기 때문임
     *
     * */
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

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

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}

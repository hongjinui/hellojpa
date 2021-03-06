package hellohjpa.entity;

import lombok.ToString;

import javax.persistence.*;

@NamedQuery(name = "selectMemberBD",
    query = "select m,m.team from TB_MEMBERBOTHDIRECT m"
)

@ToString
@Entity(name = "TB_MEMBERBOTHDIRECT")
public class MemberBothDirect {

    // 양방향

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

    /*
     *   객체 연관 관계를 이용하는 방법이 아닌 단순하게 Team 객체의 PK값으로만 사용하는 방법
     *   객체 지향적이지 못한 방법
     */
//    @Column(name = "TEAM_NAME")
//    private long teamId;
//
//    public long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(long teamId) {
//        this.teamId = teamId;
//    }

    /*
     *   추천 : LAZY
     *   이유 : 꼭 필요한 시점에 한 번에 다 가져오는
     *         쿼리를 날려야 하는 경우 다른 방법이 있기때문에 그 방법을 사용하고 웬만하면 LAZY로 하자
     *
     * */
    @ManyToOne(fetch = FetchType.EAGER)
//    @ManyToOne(fetch = FetchType.LAZY)
    /*
     *   @JoinColumn
     *   영상을 보기 전에는 다른 엔티티와 join 컬럼을 설정하는 어노테이션인줄 알았는데 그런 기능을 하는 어노테이션이 아니고
     *   이 클래스와 매핑된 DB 테이블과 매핑되는 컬럼( FK )을 추가하는 어노테이션인 것 같다
     *   잘 못 이해한 내용일 수 있으니 다시 확인 필요하긴 하다
     * */
    @JoinColumn(name = "TEAM_ID")
    private TeamBothDirect team;

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

    public TeamBothDirect getTeam() {
        return team;
    }

    public void setTeam(TeamBothDirect team) {
        this.team = team;
        team.getMembers().add(this);
    }

}

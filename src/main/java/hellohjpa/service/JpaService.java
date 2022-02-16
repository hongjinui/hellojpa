package hellohjpa.service;

import hellohjpa.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class JpaService {

    public static void basic(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 단일 객체 저장, 업데이트, 조회
            Member member = new Member();
            member.setName("hellojpa");
            member.setMemberType(MemberType.ADMIN);

//         Member 객체 영속성 컨텍스트 관리 - DB에 저장향
//         1차 캐시에 저장
            em.persist(member);

//         flush() : 쿼리를 날리고 커밋을함
//         clear() : 1차 캐시를 날림
//            em.flush();
//            em.clear();

//         이미 member 객체는 jpa 관리 하에 있기 때문에 데이터를 변경 한 후 트랜잭션을 커밋하면
//         변경을 감지하여 DB에 업데이트 쿼리를 날린다.
            member.setName("byejpa");

//         조회
//         1차 캐시에 member 객체에 대한 영속성이 저장되어 있어 DB에 쿼리를 날리지 않는다
            Member findMember = em.find(Member.class, member.getId());

            // Team 객체 저장
            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
        }

    }

    public static void oneDirect(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // Team 객체 저장
            TeamOneDirect team = new TeamOneDirect();
            team.setName("개발실");
            em.persist(team);

            // Member 객체 저장
            MemberOneDirect member = new MemberOneDirect();
            member.setName("홍진의");
            member.setMemberType(MemberType.ADMIN);
            member.setTeam(team);
            em.persist(member);

            // Member 객체 저장
            MemberOneDirect member2 = new MemberOneDirect();
            member2.setName("호랑이");
            member2.setMemberType(MemberType.MEMBER);
            member2.setTeam(team);
            em.persist(member2);

            // Member 객체 저장
            MemberOneDirect member3 = new MemberOneDirect();
            member3.setName("금강산");
            member3.setMemberType(MemberType.NONMEMBER);
            member3.setTeam(team);
            em.persist(member3);

            MemberOneDirect findMember = em.find(MemberOneDirect.class, 4L);

            // MemberOneDirect 객체는 TeamOneDirect 객체를 참조하고 있기 때문에 조회가 가능하다.
            // fetch LAZY 전력으로 콘솔에 찍을 때 조회 쿼리를 날린다.
            System.out.println("MemberOneDirect = " + findMember);
            System.out.println("TeamOneDirect = " + findMember.getTeam());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
        }

    }

    public static void bothDirect(EntityManagerFactory emf){

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            TeamBothDirect team = new TeamBothDirect();
            team.setName("AcrossTeam");
            em.persist(team);

            MemberBothDirect member = new MemberBothDirect();
            member.setName("새로운맴버입니다");
            member.setMemberType(MemberType.MEMBER);
            member.setTeam(team);
            em.persist(member);

            MemberBothDirect member2 = new MemberBothDirect();
            member2.setName("새로운맴버입니다2");
            member2.setMemberType(MemberType.MEMBER);
            member2.setTeam(team);
            em.persist(member2);

            // 양방향 매핑에서는
            // MemberAcrossDirect -> TeamAcrossDirect 참고
            // TeamAcrossDirect -> MemberAcrossDirect 참고
            // 이기 때문에 하나의 객채만 조회해도 둘 다 볼 수 있음ㅋ 개굳
            // 데이터 수정은 연관 관계의 주인에서만 가능하다 - 중요
//            MemberAcrossDirect findMember = em.find(MemberAcrossDirect.class, 6L);
//            System.out.println("findMember = " + findMember);
//
//            TeamAcrossDirect findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam);


            // 연관 관계의 주인이 아닌 객체(TeamAcrossDirect)에서 주인 객체(MemberAcrossDirect)를 조회하려면
            // 주인 객체에서 주인이 아닌 객체를 할당해주어야 한다.
//            TeamAcrossDirect findTeam = em.find(TeamAcrossDirect.class, 5L);
//            System.out.println("findTeam = " + findTeam);
//            List<MemberAcrossDirect> members = findTeam.getMembers();
//            for (MemberAcrossDirect memberAcrossDirect : members) {
//                System.out.println("memberAcrossDirect = " + memberAcrossDirect);
//            }

            MemberBothDirect member3 = new MemberBothDirect();
            member3.setName("새로운맴버입니다3");
            member3.setMemberType(MemberType.MEMBER);
            em.persist(member3);

            // 연관 관계의 주인이 아닌 객체를 통해 업데이트 하기때문에
            // FK가 들어가지 않는다. 자주 하는 실수임 - 중요함
            team.getMembers().add(member3);

            em.clear();

            em.find(MemberBothDirect.class, 8L);



        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            tx.commit();
            em.close();
        }
    }

}

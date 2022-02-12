package hellohjpa;

import hellohjpa.entity.Member;
import hellohjpa.entity.MemberType;
import hellohjpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("teamA");


        // Team 객체 영속성 부여? - DB에 저장
        // Make an instance managed and persistent.
        em.persist(team);

        Member member = new Member();
        member.setName("안녕하세요");
        member.setMemberType(MemberType.ADMIN);
        member.setTeam(team);

        member.setLocalDate(LocalDate.now());
        member.setLocalDateTime(LocalDateTime.now());

        // Member 객체 영속성 부여? - DB에 저장
        // Make an instance managed and persistent.
        em.persist(member);


        // 업데이트
//        team = new Team();
//        team.setName("teamB");
//        em.persist(team);
//        member.setTeam(team);

        // 쿼리를 한 번에 다 날림
        // 하지 않으면 캐시로 select 쿼리를 볼 수 없
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = findMember.getTeam();

        List<Member> members = findTeam.getMembers();
        for (Member member1 : members) {
            System.out.println("member1 = " + member1);
        }

        tx.commit();

        em.close();
        emf.close();


    }
}

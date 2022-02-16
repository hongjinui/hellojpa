package hellohjpa.service;


import hellohjpa.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.*;

public class JpqlService {

    public static void basicJpql(EntityManagerFactory emf) {

        // 단일 객체

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // @Entity name 속성으로 TB_MEMBER을 선언 했기 때문에 Member 대신 넣음
            /*
                라이크 검색 쿼리
                select
                    member0_.id as id1_0_,
                    member0_.MEMBER_TYPE as member_t2_0_,
                    member0_.USER_NAME as user_nam3_0_
                from
                    TB_MEMBER member0_
                where
                    member0_.USER_NAME like '%byejpa%'
            */
            String likeJpql = "select m from TB_MEMBER m where m.name like '%byejpa%'";

            List<Member> members = em.createQuery(likeJpql, Member.class).getResultList();

            for (Member member : members) {
                System.out.println("member = " + member);
            }

            /*
                파라미터 세팅 쿼리
                select
                    member0_.id as id1_0_,
                    member0_.MEMBER_TYPE as member_t2_0_,
                    member0_.USER_NAME as user_nam3_0_
                from
                    TB_MEMBER member0_
                where
                    member0_.MEMBER_TYPE=?
            */
            String setParamJpql = "select m from TB_MEMBER m where m.memberType = :memberType";
            List<Member> tMembers = em.createQuery(setParamJpql, Member.class)
                    .setParameter("memberType", MemberType.ADMIN)
                    .getResultList();

            for (Member tMember : tMembers) {
                System.out.println("tMember = " + tMember);
            }

            /*
                페이징 쿼리
                select
                team0_.id as id1_3_,
                team0_.TEAM_NAME as team_nam2_3_
                from
                TB_TEAM team0_ limit ?
            */
            String pagingJpql = "select t from TB_TEAM t";
            List<Team> teams = em.createQuery(pagingJpql, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(5)
                    .getResultList();

            for (Team team : teams) {
                System.out.println("pagingTeam = " + team);
            }

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

            List<MemberOneDirect> members = em.createNamedQuery("selectMemberOD", MemberOneDirect.class)
//                    .setParameter("memberName", "홍진의")
                    .getResultList();

            for (MemberOneDirect member : members) {
                System.out.println("member = " + member);
            }

//            System.out.println("selectMember = " + member);
//            System.out.println("selectMember = " + member.getTeam().getName());


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
//
//            List<MemberBothDirect> members = em.createNamedQuery("selectMemberBD", MemberBothDirect.class)
//                    .getResultList();
//
//            for (MemberBothDirect member : members) {
//                System.out.println("selectMember Both = " + member);
//            }

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            tx.commit();
            em.close();
        }

    }

}

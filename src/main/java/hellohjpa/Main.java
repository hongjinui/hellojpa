package hellohjpa;

import hellohjpa.entity.Member;
import hellohjpa.entity.MemberType;
import hellohjpa.entity.Team;
import hellohjpa.service.JpaService;
import hellohjpa.service.JpqlService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {

        // EntityManagerFactory 객체를 생성해서
        // 각각의 메소드에 EntityManager 객체를 생성하는데 이게 맞나?
        // 비즈니스 로직이라고 가장했을 때, EntityManager 객체 하나로 모든 메소드를 실행해야할까?
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // Member, Team 객체가 서로 연관 관계가를 가지지 않음
        JpaService.basic(emf);

        // MemberOneDirect ->  TeamOneDirect 단방향
        JpaService.oneDirect(emf);

        // MemberAcrossDirect <-> TeamAcrossDirect 양방향
        JpaService.AcrossDirect(emf);

        // jpql 단일 객체
        JpqlService.basicJpql(emf);

        emf.close();

    }
}

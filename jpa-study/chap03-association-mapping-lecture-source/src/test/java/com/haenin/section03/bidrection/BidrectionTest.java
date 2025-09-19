package com.haenin.section03.bidrection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class BidrectionTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory =
                Persistence.createEntityManagerFactory(
                        "jpatest");
    }

    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager(){
        entityManager.close();
    }

    @AfterAll
    public static void closeFactoru(){
        entityManagerFactory.close();
    }

    @Test
    public void 양방향_연관관계_매핑_조회_테스트(){
        int menuCode = 10;
        int categoryCode = 10;

        /* 설명. 1. Menu를 기준으로 */
        /* 설명. 연관관계의 주인(자식 엔티티, ManyToOne, FetchType.EAGER가 디폴트)
        *       은 left join문으로 조회한다. */
        Menu fouundMenu = entityManager.find(Menu.class,menuCode);
        // Menu의 toString에서 Category와 관련된 필드를 주석
        // Category 엔티티 사용을 멈춰줌으로써 순환참조 방지
        System.out.println("10번 메뉴: " + fouundMenu);
        System.out.println("10번 메뉴의 카테고리: " + fouundMenu.getCategory());
        System.out.println("10번 메뉴의 카테고리와 같은 메뉴들: ");
        fouundMenu.getCategory().getMenuList().forEach(System.out::println);

        /* 설명. 2. Category를 기준으로 */
        /* 설명. 부모 엔티티(OneToMany, FetchType.LAZY가 디폴트)
                는 해당 테이블만 조회한다. */
        Category foundCategory = entityManager.find(Category.class,categoryCode);
        System.out.println("foundCategory = " + foundCategory);
        System.out.println("10번 카테고리의 메뉴들: ");
        foundCategory.getMenuList().forEach(System.out::println);

        /* 설명.
        *   양방향 관계는 toString()메소드에서 연관관계에 있는
        *   엔티티의 toString()을 호출하는 것을 신경써야 한다.
        *   1. 순환참조로 인한 stackOverflow를 발생시키므로
        *   최소 한쪽 엔티티에서는 주석 처리를 해주어야한다.
        *   2. 아무리 FetchType.LAZY라도 toString에서
        *   연관관계의 필드가 있으면 toString()을 호출하여
        *   추가 select문을 발생시킨다(N+1문제 발생)
        *
        *   설명.
        *    결론은 -> 왠만하면 연관관계를 가진 엔티티는
        *    toString에서 연관관계와 관련된 필드는 생략하는 편이 좋다.
        * */
    }
}

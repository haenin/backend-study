package com.haenin.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class SimpleJPQLTest {
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
    public void TypedQuery를_이용한_단일행_조회_테스트(){
        String jpql ="SELECT m.menuName FROM menu_section01 m where m.menuCode = 7";
        /* 설명. 조회 결과 반환되는 값을 알고 있을때
        *       TyperQuery를 활용하며
        *       단일 행인지 다중 행인지 구분해서 사용 */
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        // 결과가 단일향 행임을 알고
        // getSingleResult() 호출
        String resultMenuName = query.getSingleResult();
        System.out.println("resilMenuNam = " + resultMenuName);

        Assertions.assertEquals("민트미역국",resultMenuName);
    }

    @Test
    public void Query를_이용한_단일행_단일열_조회_테스트(){
    /*  String jpql ="SELECT m.menuName FROM menu_section01 m where m.menuCode = 7";

        Query query = entityManager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();
        System.out.println("resultMenuName = " + resultMenuName);

        Assertions.assertTrue(resultMenuName instanceof String);
        Assertions.assertEquals("민트미역국",resultMenuName);
        */
        /* 설명. 일부 속성만 추출하는 Projection(부분 다중열)
                의 하나의 경우만 테스트 */
        /* 설명. 단일행 일부 다중열(Object[]일 시)
        *       -> 전체 속성(컬럼) 또는 단일 속성(컬럼)이 아닌 경우*/
        String jpql ="SELECT m.menuName, m.menuPrice FROM menu_section01 m where m.menuCode = 7";
        Query query = entityManager.createQuery(jpql);
        List<Object[]> resultColumns = query.getResultList();

        Object[] resultRow = resultColumns.get(0);
        Arrays.stream(resultRow).forEach(System.out::println);

        Assertions.assertTrue(resultRow[0] instanceof String);
        Assertions.assertTrue(resultRow[1] instanceof Integer);
    }
    @Test
    public void TypedQuery를_이용한_다중행_다중열_조회_테스트(){
        String jpql ="SELECT m FROM menu_section01 m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> foundeMenuList = query.getResultList();
        foundeMenuList.forEach(System.out::println);

        Assertions.assertTrue(!foundeMenuList.isEmpty());
    }

    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트() {
        String jpql ="SELECT distinct m.categoryCode FROM menu_section01 m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql,Integer.class);

        List<Integer> foundeMenuList = query.getResultList();
        foundeMenuList.forEach(System.out::println);

        Assertions.assertTrue(!foundeMenuList.isEmpty());
    }

    @Test
    public void in_연산자를_활용한_조회_테스트(){
        String jpql ="SELECT m FROM menu_section01 m WHERE m.categoryCode IN (6,10)";

        List<Menu> foundeMenuList = entityManager.createQuery(jpql, Menu.class).getResultList();
        foundeMenuList.forEach(System.out::println);

        Assertions.assertTrue(!foundeMenuList.isEmpty());
    }

    @Test
    public void like_연산자를_활용한_조회_테스트(){
        String jpql ="SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%마늘%'";

        List<Menu> foundeMenuList = entityManager.createQuery(jpql, Menu.class).getResultList();
        foundeMenuList.forEach(System.out::println);

        Assertions.assertTrue(!foundeMenuList.isEmpty());
    }

    /* 설명. JPQL은 기본적으로 SQL문과 크게 다르지 않지만
    *       set operator나 subquery 활용 등에서
    *       차이가 나는 부분이 있다.*/

}

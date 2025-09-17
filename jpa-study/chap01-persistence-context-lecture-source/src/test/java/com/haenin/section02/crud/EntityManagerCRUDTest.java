package com.haenin.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EntityManagerCRUDTest {
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
    public void 메뉴코드로_메뉴_조회_테스트(){
        int menuCode = 2;
        /* 설명. entityManager를 통해 여러번 find를 해도
        *       select는 한번만 동작한다.
        *       => DB I/O 횟수 줄임 */
        // jpa는 동일성을 보장한다.
        // select문을 한번만 사용하고 재사용
        // db io 즉 db와의 입출력을 줄일 수 있다.
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        Menu foundMenu2 = entityManager.find(Menu.class, menuCode);


        /* 설명. 단정문은 두개 이상 가능하며
        *       동일성 보장 확인 */
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(foundMenu,foundMenu2);
        System.out.println("foundMenu = " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트(){
        Menu menu = new Menu();
        menu.setMenuName("꿀발린추어탕");
        menu.setMenuPrice(7000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        EntityTransaction transaction =
                entityManager.getTransaction();
        // 맡기기전에 트랜잭션 시작
        transaction.begin();

        try {
            // 영속상태로 바꿈
            entityManager.persist(menu);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
    @Test
    public void 메뉴_이름_수정_테스트(){
        // 매니저는 처음에 컨텍스트에 존재하는게 없음
        // find로 셀렉트하는 순간 영속성이 된다.

        /* 설명. 26번 메뉴 엔티티를
        *       영속 상태로 만들어 받은 다음 */
        Menu menu = entityManager.find(Menu.class,26);
        System.out.println("수정전 menu: " + menu);

        String menuNameToChange ="갈치스무디";

        EntityTransaction transaction =
                entityManager.getTransaction();
        // 맡기기전에 트랜잭션 시작
        transaction.begin();
        try {
            // 영속상태로 바꿈
            menu.setMenuName(menuNameToChange);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Test
    public void 메뉴_삭제하기_테스트(){
        Menu menuToRemove = entityManager.find(Menu.class, 26);

        EntityTransaction transaction =
                entityManager.getTransaction();
        // 맡기기전에 트랜잭션 시작
        transaction.begin();
        try {
            // 영속상태로 바꿈
            entityManager.remove(menuToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}

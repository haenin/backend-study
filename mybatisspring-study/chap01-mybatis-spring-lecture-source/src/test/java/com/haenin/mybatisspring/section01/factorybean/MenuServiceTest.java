package com.haenin.mybatisspring.section01.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MenuServiceTest {

    // 테스트코드시에는 필드 주입으로 간단히 함
    @Autowired
    private MenuService menuService;

    @DisplayName("주문 가능 상태별 메뉴 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings={"Y" ,"N"})
    // assertDoesNotThrow
        // 주어진 코드 블록(람다식)이 실행될 때 예외가 발생하지 않아야 한다는 것을 확인하는 메서드
    void testFindAllMenus(String orderableStatus){
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MenuDTO> menus = menuService.findAllMenuByOrderableStatus(orderableStatus);
                    menus.forEach(System.out::println);
                }
        );
    }
}
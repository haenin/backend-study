package com.haenin.transactional.section01.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
/* 설명. 서비스 메소드 내부에서 모든 DML관련 작업에 예외 없이 잘 동작하면 commit, 예외가 발생하면 rollback이 적용된다. */
/* 설명. 전파행위 옵션(propagation)
 *  REQUIRED : 진행 중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션에서 실행하되 그렇지 않은 경우 새 트랜잭션을 시작해서 실행한다.
 *  REQUIRED_NEW : 항상 새 트랜잭션을 시작해 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단시킨다.
 *  SUPPORTS : 진행중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션 내에서 실행하되, 그렇지 않을 경우 트랜잭션 없이 실행한다.
 *  NOT_SUPPORTED : 트랜잭션 없이 현재 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단한다
 *  MANDATORY : 반드시 트랜잭션을 걸고 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.
 *  NEVER : 반드시 트랜잭션 없이 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.
 *  NESTED : 진행중인 트랜잭션이 있으면 현재 메소드를 이 트랜잭션의 중첩트랜잭션 내에서 실행한다. 진행중인 트랜잭션이 없으면 새 트랜잭션을
 *           실행한다.
 *           배치 실행 도중 처리 할 업무가 백만개라고 하면 10만개씩 끊어서 커밋하는 경우 중간에 잘못 되어도 중첩 트랜잭션을 롤백하면 전체가
 *           아닌 10만개만 롤백된다.
 *           세이브포인트를 이용하는 방식이다. 따라서 세이브포인트를 지원하지 않는 경우 사용 불가능하다.
 */

/* 설명. 격리레벨 (동시성)
 *  DEFAULT : DB의 기본 격리 수준을 사용한다. 대다수는 READ_COMMITTED가 기본 격리 수준이다.
 *  READ_UNCOMMITTED : 다른 트랜젝션이 아직 커밋하지 않은 값을 다른 트랜젝션이 읽을 수 있다.
 *                     따라서 오염된 값을 읽거나, 재현 불가능한 값 읽기, 허상 읽기 등의 문제가 발생할 수 있다.(모든 동시성 문제 발생)
 *  READ_COMMITTED : 트랜젝션이 다른 트랜젝션에서 커밋한 값만 읽을 수 있다.
 *                   오염된 값 읽기 문제는 해결되지만 재현 불가능한 값 읽기 및 허상읽기는 여전히 발생할 수 있다.(다른 로우 수정 및 추가는 막을 수 없다.)
 *  REPEATABLE_READ : 트랜젝션이 어떤 필드를 여러 번 읽어도 동일한 값을 읽도록 보장한다.
 *                    트랜젝션이 지속되는 동안에는 다른 트랜젝션이 해당 필드를 변경할 수 없다.
 *                    오염된 값 읽기, 재현 불가능한 값 읽기는 해결되지만 허상읽기는 여전히 발생할 수 있다.(다른 로우 추가는 막을 수 없다.)
 *  SERIALIZABLE : 트랜젝션이 테이블을 여러 번 읽어도 정확히 동일한 로우를 읽도록 보장한다. 트랜젝션이 지속되는 동안에는
 *                 다른 트랜젝션이 해당 테이블에 삽입, 수정, 삭제를 할 수 없다.
 *                 동시성 문제는 모두 해소되지만 성능은 현저하게 떨어진다. (모든 동시성 문제는 막을 수 있다.)
 * 설명.
 *  오염된 값 : 하나의 트랜젝션이 데이터를 변경 후 잠시 기다리는 동안 다른 트랜젝션이 데이터를 읽게 되면,
 *           격리레벨이 READ_UNCOMMITTED인 경우 아직 변경 후 커밋하지 않은 재고값을 그대로 읽게 된다.
 *           그러나 처음 트랜젝션이 데이터를 롤백하게 되면 다른 트랜젝션이 읽은 값은 더 이상 유효하지 않은 일시적인 값이 된다.
 *           이것을 오염된 값라고 한다.
 *  재현 불가능한 값 읽기 : 처음 트랜젝션이 데이터를 수정하면 수정이 되고 아직 커밋되지 않은 로우에 수정 잠금을 걸어둔 상태에다.
 *                       결국 다른 트랜젝션은 이 트랜젝션이 커밋 혹은 롤백 되고 수정잠금이 풀릴 때까지 기다렸다가 읽을 수 밖에 없게 된다.
 *                       하지만 다른 로우에 대해서는 또 다른 트랜젝션이 데이터를 수정하고 커밋을 하게 되면
 *                       가장 처음 동작한 트랜젝션이 데이터를 커밋하고 다시 조회를 한 경우 처음 읽은 그 값이 아니게 된다.
 *                       이것이 재현 불가능한 값이라고 한다.
 *  허상 읽기 : 처음 트랜젝션이 테이블에서 여러 로우를 읽은 후 이후 트랜젝션이 같은 테이블의 로우를 추가하는 경우
 *             처음 트랜젝션이 같은 테이블을 다시 읽으면 자신이 처음 읽었을 때와 달리 새로 추가 된 로우가 있을 것이다.
 *             이것을 허상 읽기라고 한다. (재현 불가능한 값 읽기와 유사하지만 허상 읽기는 여러 로우가 추가되는 경우를 말한다.)
 */

@Service
public class OrderService {
    private final OrderMapper orderMapper; // final을 붙이지 않아도 싱글톤으로 관리
    private final MenuMapper menuMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper, MenuMapper menuMapper) {
        this.orderMapper = orderMapper;
        this.menuMapper = menuMapper;
    }

    // 문제가 없으면 커밋을 날려줌
    @Transactional
    public void registNewOrder(OrderDTO orderInfo){
        /* 설명. 1. 주문한 메뉴들의 코드만 추출(DTO에서) */
//        List<Integer> menuCodes = new ArrayList<>();
//        List<OrderMenuDTO> orderMenus = orderInfo.getOrderMenus();
//        for(OrderMenuDTO orderMenu: orderMenus){
//            menuCodes.add(orderMenu.getMenuCode());
//        }
        // 스트림으로 적용
        List<Integer> menuCodes = orderInfo.getOrderMenus()
                .stream()
//                .map(orderMenu -> orderMenu.getMenuCode())
                .map(OrderMenuDTO::getMenuCode)
                .collect(Collectors.toList());

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("menuCodes",menuCodes);

        /* 설명. 2. 주문한 메뉴별로 Menu엔티티에 담아서 조회(select)해 오기
        *       -> 부가적인 메뉴의 정보추출 (단가를 가져오기) */
        List<Menu> menus = menuMapper.selectMenuByMenuCodes(map);
        menus.forEach(System.out::println);

        /* 설명. 3. 이 주문건에 대한 총 합계를 계산 */
        int totalOrderPrice = calcTotalPrice(
                orderInfo.getOrderMenus(), menus);

        /* 설명. 4. 1부터 3까지 해서 tbl_order 테이블에 추가
        *       -> insert*/
        /* 설명. 4-1. OrderDTO -> Order */
        Order order = new Order(
                orderInfo.getOrderDate(),orderInfo.getOrderTime(),
                totalOrderPrice);
        /* 설명. 4-2. Order로 tbl_order 테이블에 insert */
        orderMapper.insertOrder(order);
        // select key를 활용해서
        // insert 이후 select를 통해 한번의 DB와의 I/O를 발생시킴
        System.out.println("주문 성공 후 방금 주문한 번호가"
                            + "Order 엔티티에 들어있는지 확인: "
                            + order.getOrderCode());

        /* 설명. 5. tbl_order_menu 테이블에도 주문한 메뉴 갯수 만큼
        *          메뉴를 추가 -> insert*/
        List<OrderMenuDTO> orderMenuDTO =
                orderInfo.getOrderMenus();
        for(OrderMenuDTO menuDTO: orderMenuDTO){
            orderMapper.insertOrderMenu(new OrderMenu(
                    order.getOrderCode(),
                    menuDTO.getMenuCode(),
                    menuDTO.getOrderAmount()));
        }
    }

    /* 설명. 사용자가 주문한 각 메뉴와 수량에 따른
    *       총 주문금액 계산용 메소드 */
    private int calcTotalPrice(List<OrderMenuDTO> orderMenus, List<Menu> menus) {
        int totalPrice = 0;
        int orderMenuSize = orderMenus.size(); // 사용자가 주문한 메뉴의 종류
        // 사용자가 주문한 메뉴의 종류만큼 반복
        for (int i = 0; i < orderMenuSize; i++) {
            // 메뉴 수랑 추출을 위해
            OrderMenuDTO orderMenu = orderMenus.get(i);
            // 메뉴 단가 추출을 위해
            Menu menu = menus.get(i);
            totalPrice += orderMenu.getOrderAmount() * menu.getMenuPrice();
        }
        return totalPrice;
    }
}

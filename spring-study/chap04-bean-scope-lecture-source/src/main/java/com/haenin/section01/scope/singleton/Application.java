package com.haenin.section01.scope.singleton;

import com.haenin.common.Product;
import com.haenin.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ApplicationContext cotext =
                new AnnotationConfigApplicationContext
                        (ContextConfiguration.class);
        Arrays.stream(cotext.getBeanDefinitionNames()).forEach(System.out::println);

        /* 설명. 붕어빵, 우유, 물을 진열 */
        Product carpBread = cotext.getBean("carpBread", Product.class);
        Product milk = cotext.getBean("milk", Product.class);
        Product water = cotext.getBean("water", Product.class);

        /* 설명. 첫 번째 손님 입장 후 쇼핑 카트를 꺼내 물건을 담는다. */
        ShoppingCart cart1 = cotext.getBean("shoppingCart",ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);
        System.out.println("첫 번째 손님의 카트: " + cart1);

        /* 설명. 두 번째 손님 입장 후 쇼핑 카트에 물건을 담는다. */
        ShoppingCart cart2 = cotext.getBean("shoppingCart",ShoppingCart.class);
        cart2.addItem(water);
        System.out.println("두 번째 손님의 카트: " + cart2);

    }
}

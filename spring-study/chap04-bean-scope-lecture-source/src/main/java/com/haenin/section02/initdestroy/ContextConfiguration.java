package com.haenin.section02.initdestroy;

import com.haenin.common.Beverage;
import com.haenin.common.Bread;
import com.haenin.common.Product;
import com.haenin.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

    @Configuration
    public class ContextConfiguration {
        @Bean
        public Product carpBread() {
            return new Bread("붕어빵", 1000, new java.util.Date());
        }

        @Bean
        public Product milk() {
            return new Beverage("딸기우유", 1500, 500);
        }

        @Bean
        public Product water() {
            return new Beverage("지리산암반수", 3000, 1500);
        }

        @Bean
        public ShoppingCart shoppingCart() {
            return new ShoppingCart();
        }

        /* 설명. Bean 타입의 클래스에 정의 된 메소드를 활용해서
        *       bean 생성 및 소멸 시점에 호출할 수 있다.*/ // 로그르 찍어야 할 때
        @Bean(initMethod = "openShop", destroyMethod = "closeShop")
        public Owner ower(){
            return new Owner();
        }
}

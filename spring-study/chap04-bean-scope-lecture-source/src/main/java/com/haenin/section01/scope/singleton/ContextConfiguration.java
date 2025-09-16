package com.haenin.section01.scope.singleton;

import com.haenin.common.Beverage;
import com.haenin.common.Bread;
import com.haenin.common.Product;
import com.haenin.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {
    // 네가지는 싱글톤
    @Bean
    public Product carpBread(){
        return new Bread("붕어빵", 1000, new java.util.Date());
    }
    @Bean
    public Product milk() {
        return new Beverage("딸기우유", 1500, 500);
    }
    @Bean
    public Product water(){
        return new Beverage("지리산암반수", 3000, 1500);
    }

    /* 설명.
    *   bean 스코프를 수정하게 되면 bean의 life cycle을
    *   다른 주기로 가져갈 수 있다. */
    @Bean
//    @Scope("singleton")
    @Scope("prototype") // 매번 새로운 bean 객체 할당
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }



}

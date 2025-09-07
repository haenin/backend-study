package com.haenin.section03.properties.subsection01;

import com.haenin.common.Beverage;
import com.haenin.common.Bread;
import com.haenin.common.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/section03/properties/subsection01/product-info.properties")
public class ContextConfiguration {
    /* 설명.
    *   DI와는 별개로 외부 리소스로부터 키값을 활용해 value를 불러와
    *   변수에 대입이 가능하다.
    *   1. 코드 상에서 실제 대입되는 값을 숨길 수 있음
    *   2. 외부 리소스 수정을 통해 서버가 중단되지 않고도 값이 수정될 수 있다. */
    @Value("${bread.carpbread.name}") // 설정파일로부터 불러와서 변수..
    private String carpBreadName;
//    private String carpBreadName = "붕어빵";
// 원래 이렇게.. 하드코딩 X 1.보안성 2.실시간 수정가능(서버가 도는 상황에서도 수정가능)
    @Value("${bread.carpbread.price}")
    private int carpBreadPrice;

    @Value("${beverage.milk.name}")
    private String milkName;

    @Value("${beverage.milk.price}")
    private int milkPrice;

    @Value("${beverage.milk.capacity}")
    private int milkCapacity;

    @Bean
    public Product carpBread(){
        return new Bread(carpBreadName, carpBreadPrice, new java.util.Date());
    }

    @Bean
    public Product milk(){
        return new Beverage(milkName, milkPrice,milkCapacity);
    }
    @Bean
    public Product water(@Value("${beverage.water.name}") String waterName,
                         @Value("${beverage.water.price}") int waterPrice,
                         @Value("${beverage.water.capacity}") int waterCapacity)
    {
        return new Beverage(waterName,waterPrice,waterCapacity);
    }
}

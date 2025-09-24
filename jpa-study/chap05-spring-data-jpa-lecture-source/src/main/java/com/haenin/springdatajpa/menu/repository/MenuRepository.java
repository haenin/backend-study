package com.haenin.springdatajpa.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.haenin.springdatajpa.menu.entity.Menu;

import java.util.List;

// 인터페이스는 콩이 될 수 없다
// JpaRepository를 상속받음으로써 스프링이 하위구현체 객체를 만들어서 콩으로 만들어준다.
// 제네릭안에 객체타입과 pk타입을 넣어준다.
public interface MenuRepository
        extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuPriceGreaterThan(int menuPrice);

    List<Menu> findByMenuPriceBetween(int menuPrice, int i);
}

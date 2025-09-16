package com.haenin.section03.remix;


import java.util.List;

/* 설명.
 *  xml방식(쿼리는 xml)과 java방식(dao가 인터페이스이자 간단한 추상메소드로 구성)의 장점을 혼용하기 위한 방법
 *  1. xml 파일은 mapper용 인터페이스와 같은 이름으로 작성 되어야 한다.
 *  2. xml 파일은 mapper용 인터페이스와 같은 경로에 작성 되어야 한다.
 *  3. xml 파일의 namespace는 mapper용 인터페이스로 작성되어야 한다.(feat. 풀네임으로)
 *  4. xml 파일의 쿼리 id와 추상메소드의 메소드명이 일치되어야 한다.
 * */
/* 설명. 쿼리를 포함한 Mapper용 인터페이스이자 DAO계층 역할도 한다. */
public interface MenuMapper {

    List<MenuDTO> selectAllMenus();

    MenuDTO selectMenuByMenuCode(int menuCode);

    int insertMenu(MenuDTO menu);

    int updateMenu(MenuDTO menu);

    int deleteMenu(int menuCode);
}

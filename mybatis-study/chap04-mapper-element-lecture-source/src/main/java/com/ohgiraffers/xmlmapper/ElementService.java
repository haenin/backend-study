package com.ohgiraffers.xmlmapper;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import static com.ohgiraffers.xmlmapper.Template.getSqlSession;

public class ElementService {
    public void selectResultMapTest() {
        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<MenuDTO> menus = mapper.selectResultMapTest();
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    public void selectResultMapAssociationTest() {
        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<MenuAndCategoryDTO> menus = mapper.selectResultMapAssociationTest();
        menus.forEach(System.out::println);

        System.out.println("첫 번째 메뉴의 카테고리 이름 조회: " + menus.get(0).getCategory().getCategoryName());

        sqlSession.close();
    }

    public void selectResultMapCollectionTest() {
        SqlSession sqlSession = getSqlSession();
        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<CategoryAndMenuDTO> categories = mapper.selectResultMapCollectionTest();
        categories.forEach(System.out::println);

        System.out.println("'식사' 카테고리의 메뉴들: ");
        for (CategoryAndMenuDTO category: categories) {
            if("한식".equals(category.getCategoryName()))
                System.out.println(category.getMenus());
        }

        categories.stream()
                .filter((category) -> "한식".equals(category.getCategoryName()))
                .forEach(c -> System.out.println(c.getMenus()));

        sqlSession.close();

    }
}
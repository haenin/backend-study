package com.ohgiraffers.xmlmapper;

import java.util.List;

public interface ElementMapper {

    List<MenuDTO> selectResultMapTest();

    List<MenuAndCategoryDTO> selectResultMapAssociationTest();

    List<CategoryAndMenuDTO> selectResultMapCollectionTest();
}

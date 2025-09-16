package com.ohgiraffers.xmlmapper;

import java.util.List;
import java.util.Objects;

public class CategoryAndMenuDTO {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
    private List<MenuDTO> menus;

    public CategoryAndMenuDTO() {
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryAndMenuDTO that = (CategoryAndMenuDTO) o;
        return categoryCode == that.categoryCode && Objects.equals(categoryName, that.categoryName) && Objects.equals(refCategoryCode, that.refCategoryCode) && Objects.equals(menus, that.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode, categoryName, refCategoryCode, menus);
    }

    @Override
    public String toString() {
        return "CategoryAndMenuDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menus=" + menus +
                '}';
    }
}

package com.ohgiraffers.xmlmapper;

import java.util.Objects;

public class CategoryDTO {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;    // null 값이 들어올 수 있는 필드는 Wrapper class 활용

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return categoryCode == that.categoryCode && Objects.equals(categoryName, that.categoryName) && Objects.equals(refCategoryCode, that.refCategoryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCode, categoryName, refCategoryCode);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}

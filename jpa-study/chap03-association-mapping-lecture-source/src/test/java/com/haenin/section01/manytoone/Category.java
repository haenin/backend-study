package com.haenin.section01.manytoone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="category_section01")
@Table(name ="tbl_category")
public class Category {
    @Id
    @Column(name="category_code")
    private int categorycode;

    @Column(name="category_name")
    private String categoryName;

    /* 설명. Nulll 값이 들어갈 수 있는
    *       엔티티의 필드는
    *       참조자료형을 쓰자 */
    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    public Category() {
    }

    public Category(int categorycode, String categoryName, Integer refCategoryCode) {
        this.categorycode = categorycode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(int categorycode) {
        this.categorycode = categorycode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categorycode=" + categorycode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}

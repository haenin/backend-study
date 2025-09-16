package com.haenin.section02.javaconfig;

import org.apache.ibatis.annotations.Select;

public interface Mapper {
    // 메소드이름이 쿼리이름이 되는 것
    @Select("SELECT NOW()")
    java.util.Date selectNow();
}

package com.haenin.transactional.section01.annotation;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// 이 친구를 매퍼로 등록하겠다랑 콩이 된다
// 콩 -> 객체로 되어 싱글톤하게 스프링이 관리한다
@Mapper
public interface MenuMapper {
    List<Menu> selectMenuByMenuCodes(Map<String, List<Integer>> map);
}

package com.haenin.section01.common;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    /* 설명. ResultSet 대신에 자바에서는 Map같은 Collection 형태로 담게 됨
    *       ( ResultSet보다 좋음(ResultSet은 소모성) ) */
    private Map<Integer, BookDTO> books; // DB에서 load헤서 캐싱 한 것
    /* 설명. JDBC를 활용한 다중행 조회 결과인 ResultSet을 자바의 타입으로 받았다고 가정 */
    public BookDAOImpl() {
        books = new HashMap<>();
        books.put(1, new BookDTO(1,123456, "자바의 정석",
                "남궁성", "도우출판", new java.util.Date()));
        books.put(2, new BookDTO(2, 223344,"칭찬은 고래도 춤추게 한다.",
                "고래", "고래출한",new java.util.Date()));
    }

    @Override
    public List<BookDTO> findAllBook() {
        /* 설명. Map을 List형태로 변환
            (feat. Collection 타입을 List형태로 바꾸는 생성자 활용)*/
        return new ArrayList<>(books.values());
    }

    @Override
    public BookDTO findBookBySequenceOf(int sequence) {
        return books.get(sequence);
    }
}

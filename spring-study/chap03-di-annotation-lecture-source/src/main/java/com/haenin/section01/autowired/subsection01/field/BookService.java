package com.haenin.section01.autowired.subsection01.field;

import com.haenin.section01.common.BookDAO;
import com.haenin.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스에 관한 특별한 기능은 없음
@Service
public class BookService {
    /* 설명.
    *   필드의 @AUtowired를 추가하면 필드를 통한 의존성 객체 주입(bean 주입)으로
    *   필드 주입이라고 한다.
    *  */
    @Autowired // 자동으로 연결
    private BookDAO bookDAO; // 필드주입 private BookDAO bookDAO = new BookDAOImpl();

    // 서비스안에 메서드 하나가 도메인 이벤트 하나씩
    public List<BookDTO> findAllBook(){
        return bookDAO.findAllBook();
    }

    public BookDTO findBookBySequenceOf(int sequence) {
        return bookDAO.findBookBySequenceOf(sequence);
    }
}

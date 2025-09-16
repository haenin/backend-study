package com.haenin.section01.autowired.subsection03.constructor;

import com.haenin.section01.common.BookDAO;
import com.haenin.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("constructorService")
public class BookService {

    private BookDAO bookDAO;
    /* 설명.
    *   BookDAO 타입의 빈 객체를 생성자를 통해 주입받는다.
    *   (기본 생성자X)
    *
    *  설명.
    *   생성자 주입의 이점
    *   1. 필드에 final 키워드를 사용할 수 있다.(오염 방지)
    *   콩으로 만든다는건 시작부터 끝까지 콩이 관리
    *   2. 순환 참조를 스프링 시작(컨테이너 생성 시)과 동시에 확인하고 에러를 발생시켜 준다.
    *   ( 세터 주입과  필드 주입은 에러발생이 바로 나지 않음 )
    *   3. field 주입 및 setter 주입의 단점을 보완
    *       (필드 주입은 간결하지만 남용할 수 있고, 이후에 setter가 없이는 수정이 불가능)
    *       (setter 주입은 불변 객체를 만들고자 함에 있어 문제가 발생할 수 있으며
    *        필요한 객체의 속성 변경을 위해서만 setter를 추가하는 것이 맞다.
    *           (가급적 변경의 여지를 남기지 않아야 한다.))
    *   4. 테스트 코드 작성이 용이하다.
    *   (생성자 주입은 @InjectionMocks를 사용하지 않고 그 자리에서 객체를 생성해서 쉽게 할 수 있음)
    * */

    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    public List<BookDTO> findAllBook(){
        return bookDAO.findAllBook();
    }

    public BookDTO findBookBySequenceOf(int sequence) {
        return bookDAO.findBookBySequenceOf(sequence);
    }
}

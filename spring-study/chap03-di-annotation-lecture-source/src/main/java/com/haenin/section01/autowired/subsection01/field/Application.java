package com.haenin.section01.autowired.subsection01.field;

import com.haenin.section01.common.BookDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context  =
                new AnnotationConfigApplicationContext("com.haenin.section01");

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);

        BookService bookService = context.getBean(BookService.class);
        /* 설명. 1. 도시 전체 조회 */
        /* 설명. 도시 잔체 조회 중 List<BookDTO>가 돌아올 것을 설정 */
        List<BookDTO> bookList = bookService.findAllBook();
        // 콜렉션에서만 한정해서 stream 생략가능 foreach문 쓸때만
//        bookList.stream().forEach(book -> System.out.println("book = " + book));
       /* 설명. 컬렉션일때 foreach 시에는 stream() 생략가능 */
        bookList.forEach(book -> System.out.println("book = " + book));

        /* 설명. 2. 도서 번호로 한 권 조회 */
        // By는 where절 의미
        System.out.println("1번 책: " + bookService.findBookBySequenceOf(1));
        System.out.println("2번 책: " + bookService.findBookBySequenceOf(2));

    }
}

package com.haenin.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {
    /* 설명.
    *   ResponseEntity란?
    *   HttpRequest를 응답하기 위해 httpEntity를 상속받아
    *   HttpStatus, HttpHeaders, HttpBody를
    *   정의하여 사용하는 클래스이다.
    *   (Http는 단순한 데이터 전송이 아니라
    *   상태 정보를 포함한 통신 프로토콜이기 때문에,
    *   올바른 상태 코드와 헤더를 제공해야
    *   클라이언트가 적절히 반응할 수 있다.
    *  */

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        this.users = new ArrayList<>();
        users.add(new UserDTO(1,
                "user01","pass01","홍길동",new java.util.Date()));
        users.add(new UserDTO(2,
                "user02","pass02","유관순",new java.util.Date()));
        users.add(new UserDTO(3,
                "user03","pass03","이순신",new java.util.Date()));
    }

    /* 설명. 1. ResponseEntity의 매개변수 있는 생성자를 통해
    *       ResponseEntity를 반환하는 핸들러 메서드*/
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers(){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(
                new MediaType("application", "json",
                        Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users",users);

        ResponseMessage responseMessage =
                new ResponseMessage(200,"조회 성공!",responseMap);

        return new ResponseEntity<>(responseMessage ,headers, HttpStatus.OK);
    }

    /* 설명. 2. 빌더를 활용한 메소드 체이닝 방식으로
    *       ResponseEntity 객체 만들기(요즘 유행)*/
    /* 설명. 빌더 패턴: 생성자/setter를 사용하지 않고
    *       내가 원하는 속성을 수정해서 불변객체로 만드는 패턴
    *       (내부 클래스 이용) */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application","json",Charset.forName("UTF-8"))
        );
        UserDTO foundUser = users.stream()
                                 .filter(user-> user.getNo() == userNo)
                                 .collect(Collectors.toList()).get(0);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("foundUser", foundUser);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200,userNo+"번 회원 조회 성공", responseMap));
    }

    /* 설명. 응답할 데이터가 따로 없다면
    *       ResponseEntity의 제네릭에 ?를 활용할 수 있다.
    *       (ResponseEntity 생성시 build()로 마무리)*/
    @PostMapping("/user")
    // <?> -> 데이터를 안보내겠다 or 타입이 정해져 있지 않을 때
    // <?> = <Object>
    // post에서 리퀘스트 바디에 담겨 들어옴
    public ResponseEntity<?> registUser(@RequestBody UserDTO newMember){
        System.out.println("newMember = " + newMember);
        
        /* 설명. 회원 insert 진행 */
        int lastUserNo= users.get(users.size()-1).getNo(); // 컬렉션에 담긴 마지막 회원의 번호
        newMember.setNo(lastUserNo + 1);
        
        users.add(newMember); // 컬렉션에 회원추가
        //return ResponseEntity.ok().build(); // 따로 보낼 내용 없으면 .build()로 마무리, 있다면.body()로 마무리
        return ResponseEntity
                .created(URI.create("entity/users/" + (lastUserNo + 1))) // Response Header 중 "location" 에 담겨 돌아옴
                .build();
    }

    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(
            @RequestBody UserDTO modiftMember,
            @PathVariable int userNo){
        UserDTO foundUser =
                users.stream().filter(user-> user.getNo() == userNo)
                        .collect(Collectors.toList()).get(0);
        foundUser.setId(modiftMember.getId());
        foundUser.setPwd(modiftMember.getPwd());
        foundUser.setName(modiftMember.getName());

        return ResponseEntity
                // 수정은 created 해도되고 ok로 해도된다.
                .created(URI.create("/entity/users/" + userNo))
                .build();

    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo){
        UserDTO foundUser =
                users.stream().filter(user -> user.getNo() == userNo)
                        .collect(Collectors.toList()).get(0);
        users.remove(foundUser);

        return ResponseEntity.noContent() // 204
                             .build();
    }
}

package com.haenin.restapi.section01.response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* 설명. 이 컨트롤러의 모든 핸들러 메소드의 응답은
*       view resolver를 활용하지 안흔다.
*       (ssr X) */
//@Controller
@RestController
@RequestMapping("/response")
public class ResponseRestController {
//    @GetMapping("/response")
    // 리스폰스바디가 있으면 뷰리졸버로 가지 않고 json형태로 변환
    /* 설명. 핸들러 메소드에 응답이 json 형태로
    *       되며 자바의 모든 타입 반환이 가능해진다. */
//    @ResponseBody
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }

    @Operation(summary = "랜덤 숫자 생성",
                description = "1부터 10까지의 랜덤한 숫자를 반환")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(mediaType = "application/json",
            schema = @Schema(type = "Integer", example = "7")))
    })
    @GetMapping("/random")
    public int getRandomNumber(){
        return (int)(Math.random() *10) + 1;
    }

    @GetMapping("/message")
    public Message getMessage(){
        return new Message(200,"메세지를 응답합니다.");
    }

    @GetMapping("/test")
    public List<Map<String, Object>> getTest(){
        List<Map<String, Object>> list =
                new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("test1", new Message(200,"성공1"));
        map.put("test2", new Message(200,"성공2"));
        map.put("maxPageLink", "http://localhost:8080/hello");
        list.add(map);

        return list;
    }

    /* 설명. Http 상태코드를 key로,
    *       메세지를 value로 하는 Map형태로 반환*/
    @GetMapping("/map")
    public Map<Integer, String> getMapping(){
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200,"정상응답"));
        messageList.add(new Message(404,"페이지를 찾을 수 없습니다."));
        messageList.add(new Message(500,"개발자의 잘못입니다."));

        return messageList.stream()
                .collect(Collectors
                        .toMap(Message::getHttpStatusCode,Message::getMessage));
    }

    /* 설명. 이미지 응답하기 */
    /* 설명.
    *   1. byte[] 방식: Files.readAllBytes() -> 파일 시스템에서 매번 직접 읽기
    *   2. WebConfig 방식: Springboot의 정적 리소스 핸들러 -> 서버 시작 시 인덱싱 + 강력한 브라우저/서버 캐싱으로 고정
    *   3. Resource 방식: Spring의 UrlResource -> 내부 캐싱 + 최적화 적용되며 부분적인 캐싱이 진행된다.
    * */
    /* 설명. 1. byte[] */
    // restapi -> 경로 상에 확장자 작성 x
    @GetMapping(value = "/image/bytes/{filename}",
                produces = MediaType.IMAGE_PNG_VALUE)
    /* @GetMapping(value = "/image/bytes/{filename}",
                produces = MediaType.IMAGE_JPEG_VALUE) */
    public byte[] getImageWithBytes(@PathVariable String filename) throws IOException {
        Path path = Paths.get("/Users/haeone/Desktop/hw19/backend-study/springboot-restapi-study/uploadFiles/img/single/"
        + filename +".png"); // 여기에 확장자 작성
        byte[] imageData = Files.readAllBytes(path);
        return imageData;
    }

    /* 설명. 2. WebConfig */
    /* 설명.
    *   직접 URL로 확장자를 포함한 파일 이름으로 접근 가능
    *   별도의 핸들러 메소드는 불필요하며 Spring이 자동으로
    *   정적 리소스를 서빙
    *   -> 서버가 켜질 당시로 고정
    *   WebMvcConfigurer를 구현하는 설정 클래스로 설정 가능
    *  */

    /* 설명. 3. Resource */
    @GetMapping(value = "/image/resource/{filename}",
                produces = MediaType.IMAGE_PNG_VALUE)
    // 스프링이 제공하는 클래스
    public ResponseEntity<Resource>
            getImageWithResource(@PathVariable String filename) throws MalformedURLException {

        /* 설명. UrlResource 파일 스스템 경로를
                Resource 객체로 변환 */
        Path path = Paths.get("/Users/haeone/Desktop/hw19/backend-study/springboot-restapi-study/uploadFiles/img/single/"
        + filename);
        Resource resource = new UrlResource(path.toUri());

        /* 설명.
        *   1. CONTENT_DISPOSITION 헤더
        *       : 파일명 지정으로 사용자 경험 향상
        *       , 브라우저가 응답 데이터를 어떻게 처리할 지 결정
        *   2. "inline" vs "attachment"
        *       : inline은 브라우저에서 바로 열어서 표시,
        *         attachment는 무조건 다운로드 대화상자 표시
        *   3. filename 속성
        *       : 브라우저가 파일을 저장할 때 사용할 기본 파일명
        *         (서버 파일명과 다르게 할 때 사용)*/
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                    "inline: filename=\""
                    + filename
                    +"\"");
//                    +".png\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);

    }
}

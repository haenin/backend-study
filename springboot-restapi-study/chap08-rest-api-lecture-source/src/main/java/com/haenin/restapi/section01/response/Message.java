package com.haenin.restapi.section01.response;

/* 설명. 응답 메세지 객체 */
// front가 리스폰스 헤더를 보지 않아도 가능
public class Message {
    private int httpStatusCode;
    private String message;

    public Message() {
    }

    public Message(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "httpStatusCode=" + httpStatusCode +
                ", message='" + message + '\'' +
                '}';
    }
}

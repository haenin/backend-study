package com.haenin.restapi.section02.responseentity;

import java.util.Map;

public class ResponseMessage {
    private int HttpStatus; // 응답 상태 코드 값
    private String message; // 응답 메세지
    private Map<String, Object> result; // 응답 데이터

    public ResponseMessage() {
    }

    public ResponseMessage(int httpStatus, String message, Map<String, Object> result) {
        HttpStatus = httpStatus;
        this.message = message;
        this.result = result;
    }

    public int getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        HttpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "HttpStatus=" + HttpStatus +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}

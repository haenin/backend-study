package com.haenin.chap05;

/* 설명. 사용자 정의형 예외 클래스 */
public class MemberRegistException extends Exception{
    public MemberRegistException(String message){
        super(message);
    }
}

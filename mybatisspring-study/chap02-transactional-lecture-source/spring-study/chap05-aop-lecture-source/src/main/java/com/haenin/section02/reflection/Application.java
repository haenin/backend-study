package com.haenin.section02.reflection;

import java.lang.reflect.*;

public class Application {
    public static void main(String[] args) {
        /* 설명.
        *   리플렉션(reflection)이란?
        *   컴파일 된 자바 코드에서 역으로 클래스를 불러 메소드 및 필드 정보를
        *   구해오는 방법이다.
        *   스프링 프레임워크, 마이바티스, 하이버네이트, jackson 등의 라이브러리에서
        *   사용된다.
        *   스프링에서 런타임 시 개발자가 등록한 빈을 애플리케이션 내에서
        *   사용할 수 있게 만든 기술이기도 하다.
        * */

        /* 설명. 1. Class 타입의 Class 메타정보 추출(클래스 타입만) */
        Class class1 = Account.class;
        System.out.println("class1 = " + class1);
        // 클래스가 하나의 타입이 되는 것
        Class class2 = new Account().getClass();
        System.out.println("class2 = " + class2);

        try {
            Class class3 = Class.forName("com.haenin.section02.reflection.Account");
            System.out.println("class3 = " + class3);

            Class class4 = Class.forName("[D");
            System.out.println("class4 = " + class4);
            // 모든 참조자료형은 클래스타입으로 구성
            Class class5 = double[].class;
            System.out.println("class5 = " + class5);

            Class class6 = Class.forName("[Ljava.lang.String;");
            System.out.println("class6 = " + class6);

            Class class7 = String[].class;
            System.out.println("class7 = " + class7);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 2. 필드 정보 추출 */
        Field[] fields = Account.class.getDeclaredFields();
        // 클래스 내부 필드가 private이어도 가져올 수 있음
        for(Field field : fields){
            System.out.println("modifiers: " + field.getModifiers()
            + ", type: " + field.getType()
            + ", name: " + field.getName());
        }

        /* 설명. 3. 생성자 정보 추출 */
        Constructor[] constructors = Account.class.getConstructors();
        for (Constructor constructor: constructors){
            System.out.println("name: " + constructor.getName());

            Class[] params = constructor.getParameterTypes();
            for(Class param: params){
                System.out.println("parameterType: " + param.getTypeName());
            }
        }

        /* 설명. reflect 기술로 생성자를 활용해 객체 생성해 보기 */
        try {
            Account account = (Account)constructors[0]
                    .newInstance("20", "111-111-222222","1234",10000);
            System.out.println("account.getBalance() = " + account.getBalance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /* 설명. 4. 메소드 정보 추출 */
        Method[] methods = Account.class.getMethods();
        Method getBalanceMethod = null;
        for (Method method: methods) {
            System.out.println(Modifier.toString(method.getModifiers()) + " "
                    + method.getReturnType().getSimpleName() + " "
                    + method.getName());

            if ("getBalance".equals(method.getName())) {
                getBalanceMethod = method;
            }
        }
            // invoke()로 호출 -> nonstatic메소드
            try {
                /* 설명. getBalance 메소드는 non-static이라
                    실행 시 해당 객체를 만들어 넘겨줘야 실행됨 (invoke)*/
                String result = (String)getBalanceMethod.invoke(((Account)constructors[2].newInstance()));
                System.out.println("result = " + result);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }

        }
    }


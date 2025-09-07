package com.haenin.common;

import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@ToString
@Data
@AllArgsConstructor
public class MemberDTO {
        private int sequence;
        private String id;
        private String pwd;
        private String name;
}

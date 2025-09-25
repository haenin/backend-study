package com.haenin.userservice.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_member")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "encrypt_pwd", nullable = false)
    private String encryptPwd;

/*    @Column(name = "pwd", nullable = false)
    private String pwd; */

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "userId", nullable = false, unique = true)
    private String userId; // 회원가입시 생성 될 고유 아이디(닉네임 개념)

}

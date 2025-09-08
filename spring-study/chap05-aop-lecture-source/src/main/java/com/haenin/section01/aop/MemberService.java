package com.haenin.section01.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberDAO memberDAO;

    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public List<MemberDTO> findAllMembers() {

        /* 설명. 로그 작성 */
        /* 설명. 스프링 시큐리티 적용 */
        System.out.println("target -> findAllMembers 실행");
        return memberDAO.selectAllMembers();
    }

    public MemberDTO findMemberBy(int index) {
        System.out.println("target -> findMemberBy 실행");
        return memberDAO.selectMemberBy(index);
    }
}

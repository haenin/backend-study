package com.haenin.userservice.service;

import com.haenin.userservice.aggregate.UserEntity;
import com.haenin.userservice.dto.MemberDTO;
import com.haenin.userservice.dto.ResponseOrderDTO;
import com.haenin.userservice.dto.UserDTO;
import com.haenin.userservice.dto.UserImpl;
import com.haenin.userservice.infrastructure.OrderServiceClient;
import com.haenin.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    OrderServiceClient orderServiceClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , ModelMapper modelMapper
            , BCryptPasswordEncoder bCryptPasswordEncoder
            , OrderServiceClient orderServiceClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public void registUser(UserDTO userDTO) {
        /* 설명. 회원 가입 할 인원에게 고유 아이디 생성 */
        userDTO.setUserId(UUID.randomUUID().toString());
        // 엄격 매핑해라
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", UserEntity.class);

        /* 설명. UserDTO로 넘어온 사용자의 암호(평문)를
        *       BCrypt 암호화 해서(다이체스트)
        *       UserEntity에 전달 */
        userEntity.setEncryptPwd(bCryptPasswordEncoder.encode(userDTO.getPwd()));
        userRepository.save(userEntity);
    }

    /* 설명. 단순 회원정보 조회에서 =>
    *   + 회원정보 + 회원의 주문내역(Order(다른 도메인)) */
    @Override
    public UserDTO getUserById(String memNo) {
        UserEntity user = userRepository.findById(Long.parseLong(memNo)).get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        /* 설명. 회원이 주문한 내역은 Order서비스에서
        *       Feign client 방식으로 조회해서 가져오기*/
        List<ResponseOrderDTO> orderList = orderServiceClient.getUserOrders(memNo);
        userDTO.setOrders(orderList);

        return userDTO;
    }

    // 유저서비스디테일 물려받아서 오버라이딩
    /* 설명. spring security 사용 시 프로바이더에서 활용할 로그인용 메소드
    *       -> UserDetails 타입을 반환하는 메소드(상속받아서 오버라이딩) */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /* 설명. 넘어온 email과 일치하는 email을 가진 회원을 조회해서
        *       UserEntity로 반환(조회) */
        // 쿼리 메소드를 활용한 email where절 활용
        UserEntity loginUser = userRepository.findByEmail(email);
        /* 설명. 사용자가 로그인 시 아이디(이메일)을 잘못 입력 했다면 */
        if(loginUser == null){
            throw new UsernameNotFoundException(email + " 이메일 아이디의 유저는 존재하지 않습니다.");
        }

        /* 설명. DB에서 조회 된 해당 Email의 회원이
        *       가진 권한들을 가져와 List<GrantedAuthority>로 전환*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ENTERPRISE"));

//        return new User(loginUser.getEmail(),
//                loginUser.getEncryptPwd(),
//                true,
//                true,
//                true,
//                true,
//                grantedAuthorities);
        // 이 객체에 username, password, authorities 빼고 나머지 담음
        MemberDTO memberDTO = new MemberDTO();
        UserImpl userImpl =
                new UserImpl(loginUser.getEmail(),
                        loginUser.getEncryptPwd(),
                        grantedAuthorities);
        userImpl.setDetails(memberDTO);

        return userImpl;
    }
}

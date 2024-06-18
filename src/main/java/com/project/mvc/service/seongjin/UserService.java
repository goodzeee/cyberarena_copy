package com.project.mvc.service.seongjin;

import com.project.mvc.dto.seongjin.AutoLoginDto;
import com.project.mvc.dto.seongjin.LoginDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.dto.seongjin.SignUpDto;
import com.project.mvc.entity.User;
import com.project.mvc.mapper.seongjin.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    // 회원가입 및 비밀번호 인코딩
    public boolean join(SignUpDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.save(dto.toEntity());
    }

    // 로그인 검증
    public LoginResult validateLogin(LoginDto dto, HttpSession session, HttpServletResponse response) {

        User foundUser = userMapper.findOne(dto.getEmail());
        if (foundUser == null) {
            return LoginResult.NO_EMAIL;
        }
        if (!passwordEncoder.matches(dto.getPassword(), foundUser.getPassword())) {
            return LoginResult.NO_PW;
        }
        if(dto.isAutoLogin()) {
            String sessionId = session.getId();
            Cookie autoLoginCookie = new Cookie("auto", sessionId);

            autoLoginCookie.setPath("/");
            autoLoginCookie.setMaxAge(60 * 60 * 24 * 90);
            response.addCookie(autoLoginCookie);
            userMapper.updateAutoLoginSession(
                    AutoLoginDto.builder()
                            .sessionId(sessionId)
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .email(dto.getEmail())
                            .build()
            );
        }
        maintainLoginState(session, foundUser);
        return LoginResult.SUCCESS;
    }

    // 실질적 로그인 세션 추가 메서드
    public static void maintainLoginState(HttpSession session, User loginUser) {
        // 세션의 수명 : 설정된 시간 OR 브라우저를 닫기 전까지
        int maxInactiveInterval = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(60 * 60); // 세션 수명 1시간 설정

        // 세션에 로그인한 회원 정보 세팅
        session.setAttribute("login", new LoginUserInfoDto(loginUser));
    }


}

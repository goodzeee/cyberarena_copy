package com.project.mvc.service.seongjin;

import com.project.mvc.dto.seongjin.*;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Review;
import com.project.mvc.entity.User;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.seongjin.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final DiscussionMapper discussionMapper;
    private final ReviewMapper reviewMapper;


    // 회원가입 및 비밀번호 인코딩
    public boolean join(SignUpDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.save(dto.toEntity());
    }

    public boolean checkIdentifier(String type, String keyword) {
        return userMapper.isExist(type, keyword);
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
        if (dto.isAutoLogin()) {
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

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {

        // 1. 쿠키 제거하기
        Cookie c = WebUtils.getCookie(request, "auto");
        if (c != null) {
            c.setPath("/");
            c.setMaxAge(0);
            response.addCookie(c);
        }
        // 2. DB에 자동로그인 컬럼들을 원래대로 돌려놓음
        String email = "";
        LoginUserInfoDto loginUser = (LoginUserInfoDto) request.getSession().getAttribute("login");
        if (loginUser != null) {
            email = loginUser.getEmail();
        }
        userMapper.updateAutoLoginSession(
                AutoLoginDto.builder()
                        .email(email)
                        .sessionId("none")
                        .limitTime(LocalDateTime.now())
                        .build()
        );
    }

    public List<ReviewLinkDto> getReviewList(String email) {

        List<ReviewLinkDto> reviews = reviewMapper.findAllByEmail(email);
        reviews.forEach(dto -> {
            if (dto.getCategoryNo() == 1) {
                dto.setCategory("movie");
            } else if (dto.getCategoryNo() == 2) {
                dto.setCategory("series");
            } else if (dto.getCategoryNo() == 3) {
                dto.setCategory("book");
            }
        });
        return reviews;
    }

    public String makeAndSaveCode(LoginUserInfoDto dto, HttpSession session) {

        String verifyCode = UUID.randomUUID().toString();
        boolean flag = userMapper.saveVerifyCode(dto.getEmail(), verifyCode);
        if (flag) {
            dto.setVerified(flag);
            dto.setVerify_code(verifyCode);
            session.removeAttribute("login");
            session.setAttribute("login", dto);
        }
        return verifyCode;
    }

    public String findEmail(String code) {
        return userMapper.findEmail(code);

    }

    public boolean changePassword(ChangePasswordDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.changePassword(dto);
    }

    public LoginUserInfoDto findUserInfo(String targetEmail) {
        User user = userMapper.findOne(targetEmail);
        return new LoginUserInfoDto(user);
    }
}

package com.project.mvc.controller.seongjin;


import com.project.mvc.dto.seongjin.*;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Review;
import com.project.mvc.service.seongjin.FollowLogService;
import com.project.mvc.service.seongjin.LoginResult;
import com.project.mvc.service.seongjin.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowLogService followLogService;

    @GetMapping("/sign-in")
    public String signInGet(HttpSession session, @RequestParam(required = false) String redirect) {
        session.setAttribute("redirect", redirect);

        return "user/login-page";
    }

    @PostMapping("/sign-in")
    public String signInPost(LoginDto dto, HttpSession session, HttpServletResponse response, RedirectAttributes ra) {

        LoginResult result = userService.validateLogin(dto, session, response);
        String redirect = (String) session.getAttribute("redirect");

        ra.addFlashAttribute("result", result);
        if (result == LoginResult.SUCCESS) {
            System.out.println("redirect = " + redirect);
            if (redirect != null) {
                session.removeAttribute("redirect");
                return "redirect:" + redirect;
            }
            return "redirect:/index";
        } else {
            return "redirect:/user/sign-in";
        }
    }

    @GetMapping("/sign-out")
    public String signOutGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        if (WebUtils.getCookie(request, "auto") != null) {
            userService.autoLoginClear(request, response);
        }
        session.removeAttribute("login");
        session.invalidate();

        return "redirect:/index";
    }

    @GetMapping("/sign-up")
    public String signUpGet(Model model) {

        model.addAttribute("enterType", "signUp");

        return "user/login-page";
    }

    @PostMapping("/sign-up")
    public String signUpPost(SignUpDto dto) {
        boolean flag = userService.join(dto);

        return flag ? "redirect:/user/sign-in" : "redirect:/user/sign-up";
    }

    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> checkGet(String type, String keyword) {
        boolean flag = userService.checkIdentifier(type, keyword);

        return ResponseEntity
                .ok()
                .body(flag);

    }

    @GetMapping("/mypage")
    public String myPageGet(Model model, HttpServletRequest request) {
        int followerSize = followLogService.getFollowList(request.getSession(), false).size();
        int followingSize = followLogService.getFollowList(request.getSession(), true).size();

        List<Discussion> discussList = userService.getDiscussList(request.getSession());
        model.addAttribute("discussions", discussList);
        List<ReviewLinkDto> reviewList = userService.getReviewList(request.getSession());
        model.addAttribute("reviews", reviewList);
        model.addAttribute("follower", followerSize);
        model.addAttribute("following", followingSize);
        return "user/mypage";
    }

    @GetMapping("/mypage/follower")
    @ResponseBody
    public ResponseEntity<?> followerList(HttpServletRequest request) {

        List<FollowExistsDto> followList = followLogService.getFollowList(request.getSession(), false);

        return ResponseEntity
                .ok()
                .body(followList);
    }

    @GetMapping("/mypage/following")
    @ResponseBody
    public ResponseEntity<?> followingList(HttpServletRequest request) {

        List<FollowExistsDto> followList = followLogService.getFollowList(request.getSession(), true);

        return ResponseEntity
                .ok()
                .body(followList);
    }


    @GetMapping("/follow")
    @ResponseBody
    public ResponseEntity<?> following(@RequestParam String userEmail, @RequestParam String targetEmail) {
        boolean flag = followLogService.follow(FollowLogRequestDto.builder()
                .userEmail(userEmail)
                .targetEmail(targetEmail)
                .build());
        return ResponseEntity
                .ok()
                .body(flag);
    }

    @GetMapping("/unfollow")
    @ResponseBody
    public ResponseEntity<?> unfollowing(@RequestParam String userEmail, @RequestParam String targetEmail) {
        boolean flag = followLogService.unfollow(FollowLogRequestDto.builder()
                .userEmail(userEmail)
                .targetEmail(targetEmail)
                .build());

        return ResponseEntity
                .ok()
                .body(flag);
    }
    @GetMapping("/verify")
    @ResponseBody
    public ResponseEntity<?> verify(HttpSession session) {
        LoginUserInfoDto loginUser = (LoginUserInfoDto) session.getAttribute("login");

        if(loginUser.isVerified()) {

            return ResponseEntity
                    .ok()
                    .body("이미 식별코드를 발급받았습니다.");
        }

        String code = userService.makeAndSaveCode(loginUser, session);

        return ResponseEntity
                .ok()
                .body(code);
    }
    @PostMapping("/find-id")
    @ResponseBody
    public ResponseEntity<?> findId(@RequestBody Map<String, Object> requestMap) {
        String code = (String) requestMap.get("code");

        String email = userService.findEmail(code);
        if(email == null) {
            return ResponseEntity
                    .status(403)
                    .body("올바르지 않은 식별코드 입니다.");
        }

        return ResponseEntity
                .ok()
                .body(email);
    }

    @PostMapping("/find-pw")
    @ResponseBody
    public ResponseEntity<?> findPw(@RequestBody Map<String, Object> requestMap) {
        String code = (String) requestMap.get("code");
        String email = (String) requestMap.get("email");
        String emailByCode = userService.findEmail(code);

        if(!email.equals(emailByCode)) {
            return ResponseEntity
                    .status(403)
                    .body("올바르지 않은 식별코드 입니다.");
        }

        return ResponseEntity
                .ok()
                .body(email);
    }

    @PostMapping("/change-password")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto) {
        boolean flag = userService.changePassword(dto);
        if(flag) {
            return ResponseEntity
                    .ok()
                    .body(flag);
        }
        return ResponseEntity
                .status(403)
                .body(flag);
    }
}

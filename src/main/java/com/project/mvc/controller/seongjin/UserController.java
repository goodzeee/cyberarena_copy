package com.project.mvc.controller.seongjin;


import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import com.project.mvc.dto.seongjin.*;
import com.project.mvc.dto.zyo.ReviewRenderingDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Review;
import com.project.mvc.service.kibeom.DiscussionService;
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
    private final DiscussionService discussionService;

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
        LoginUserInfoDto login = (LoginUserInfoDto) request.getSession().getAttribute("login");

        int followerSize = followLogService.getFollowList(login.getEmail(), false).size();
        int followingSize = followLogService.getFollowList(login.getEmail(), true).size();

        List<DiscussMyPageDto> discussList = discussionService.findMyDiscuss(login.getEmail());
        model.addAttribute("discussions", discussList);
        List<ReviewLinkDto> reviewList = userService.getReviewList(login.getEmail());
        model.addAttribute("reviews", reviewList);
        model.addAttribute("follower", followerSize);
        model.addAttribute("following", followingSize);
        return "user/mypage";
    }

    @GetMapping("/mypage/follower")
    @ResponseBody
    public ResponseEntity<?> followerList(HttpServletRequest request) {
        LoginUserInfoDto login = (LoginUserInfoDto) request.getSession().getAttribute("login");
        List<FollowExistsDto> followList = followLogService.getFollowList(login.getEmail(), false);

        return ResponseEntity
                .ok()
                .body(followList);
    }

    @GetMapping("/mypage/following")
    @ResponseBody
    public ResponseEntity<?> followingList(HttpServletRequest request) {
        LoginUserInfoDto login = (LoginUserInfoDto) request.getSession().getAttribute("login");
        List<FollowExistsDto> followList = followLogService.getFollowList(login.getEmail(), true);

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

        if (loginUser.isVerified()) {

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
        if (email == null) {
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

        if (!email.equals(emailByCode)) {
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
        if (flag) {
            return ResponseEntity
                    .ok()
                    .body(flag);
        }
        return ResponseEntity
                .status(403)
                .body(flag);
    }

    @GetMapping("/user-info/{targetEmail}")
    public String userInfoGet(@PathVariable String targetEmail, Model model, HttpServletRequest request) {
        LoginUserInfoDto login = (LoginUserInfoDto) request.getSession().getAttribute("login");
        if (login != null) {
            if (login.getEmail().equals(targetEmail)) {
                return "redirect:/user/mypage";
            }
        }
        boolean wasFollow = false;
        if (login != null) {
            wasFollow = followLogService.wasFollow(login.getEmail(), targetEmail);
        }
        int followerSize = followLogService.getFollowList(targetEmail, false).size();
        int followingSize = followLogService.getFollowList(targetEmail, true).size();
        LoginUserInfoDto userInfo = userService.findUserInfo(targetEmail);
        List<DiscussMyPageDto> discussList = discussionService.findMyDiscuss(targetEmail);
        List<ReviewLinkDto> reviewList = userService.getReviewList(targetEmail);
        model.addAttribute("wasFollow", wasFollow);
        model.addAttribute("follower", followerSize);
        model.addAttribute("following", followingSize);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("discussions", discussList);
        model.addAttribute("reviews", reviewList);
        return "user/user-info";
    }

    @GetMapping("/user-info/follower/{targetEmail}")
    @ResponseBody
    public ResponseEntity<?> userInfoFollowerListGet(@PathVariable String targetEmail, HttpServletRequest request) {

        List<FollowExistsDto> followList = followLogService.getFollowList(targetEmail, false);

        return ResponseEntity
                .ok()
                .body(followList);
    }

    @GetMapping("/user-info/following/{targetEmail}")
    @ResponseBody
    public ResponseEntity<?> userInfoFollowingListGet(@PathVariable String targetEmail, HttpServletRequest request) {

        List<FollowExistsDto> followList = followLogService.getFollowList(targetEmail, true);

        return ResponseEntity
                .ok()
                .body(followList);
    }
}

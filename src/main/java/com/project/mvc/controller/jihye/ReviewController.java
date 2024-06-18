package com.project.mvc.controller.jihye;

import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.entity.Media;
import com.project.mvc.service.jihye.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list")
    public String list(Model model) {
        List<ReviewListDto> mList = reviewService.findAllMediaWithReviews(); // 모든 미디어와 리뷰 목록 가져오기
        model.addAttribute("mediaList", mList);
        return "review/list"; // list.jsp로 이동
    }
    // 미디어에 달린 리뷰 전체조회 - GET
//    @GetMapping("/list")
//    public ResponseEntity<?> list(long mno, int pageNo, HttpSession session) {
//
//        if (mno == 0) {
//            String message = "미디어 번호는 0번이 될 수 없습니다.";
//            log.warn(message);
//            return ResponseEntity
//                    .badRequest()
//                    .body(message);
//        }
//        log.info("/review/{} : GET", mno);
//
////        ReviewListDto reviews = reviewService.getReviews(mno, new Page(pageNo, 5));
////        reviews.setLoginUser(LoginUtil.getLoggedInUser(session));
//
////        return ResponseEntity
////                .ok()
////                .body(reviews);
//        return null;
//    }


        // 리뷰 등록 요청


        // 리뷰 수정 요청


        // 리뷰 삭제 요청
}

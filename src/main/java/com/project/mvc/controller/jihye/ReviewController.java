package com.project.mvc.controller.jihye;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.request.jihye.ReviewModifyDto;
import com.project.mvc.dto.response.jihye.ReactionDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.jihye.LikeLogService;
import com.project.mvc.service.jihye.ReviewService;
import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final MediaService mediaService;
    private final LikeLogService likeLogService;
    private final MediaMapper mediaMapper;

    // 1. 리뷰 목록 조회 요청 : GET
    @GetMapping("/list/{mno}")
    public String list(@PathVariable("mno") long mno,
                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, String email,
                       HttpSession session,
                       Model model) {
        log.info("/review/list/{} : GET", mno);

        // 특정 미디어 정보 조회
        MediaDetailDto media = mediaService.detail(mno);

//        LoginUserInfoDto loginUser = (LoginUserInfoDto) session.getAttribute("login");
//        String email = loginUser.getEmail();
        ReviewListDto reviewList = reviewService.findList(mno);

            model.addAttribute("media", media);
            model.addAttribute("reviews", reviewList);

        // 리뷰 페이지로 이동
        return "reviewjsp/detail";
    }

    // 2. 리뷰 등록 요청
    // @RequestBody : 클라이언트가 전송한 데이터를 json으로 받아서 파싱
    @PostMapping("/add")
    //BindingResult 입력값 검증 결과 데이터를 갖고 있는 객체
    public ResponseEntity<?> addReview(@Validated @RequestBody ReviewPostDto dto, BindingResult result, HttpSession session) {
        log.info("/review : POST");
        log.debug("parameter - {}", dto);

        if (result.hasErrors()) {
            log.warn("Validation errors: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body("Invalid input data");
        }

        boolean flag = reviewService.register(dto, session);
        if (flag) {
            log.info("Review successfully registered");
            ReviewListDto reviewListDto = reviewService.findList(dto.getMediaNo());
            return ResponseEntity.ok(reviewListDto);
        } else {
            log.warn("Failed to register review");
            return ResponseEntity.internalServerError().body("Failed to register review");
        }
    }

    private Map<String, String> makeValidationMessageMap(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    // 3. 리뷰 수정 요청
//    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    @PostMapping("/modify")
    public ResponseEntity<?> modify(@Valid @RequestBody ReviewModifyDto dto, BindingResult result) {
        log.info("/review/modify : POST");
        log.debug("parameter - {}", dto);

        if (result.hasErrors()) {
            // 유효성 검사 에러 처리
            Map<String, String> errors = makeValidationMessageMap(result);
            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        ReviewListDto reviewListDto = reviewService.modify(dto);

        return ResponseEntity.ok().body(reviewListDto);
    }

    // 4. 리뷰 삭제 처리 요청 (GET 방식)
    @GetMapping("/delete/{mno}")
    public String getDelete(@RequestParam("reviewNo") long reviewNo, @PathVariable("mno") long mno) {
        System.out.println("/review/delete GET");
        log.info("reviewNo : {}", reviewNo);
        log.info("mno : {}", mno);

        ReviewListDto removed = reviewService.getRemove(reviewNo);

        return "redirect:/review/list/" + mno;
    }


    // 4. 리뷰 삭제 처리 요청 (DELETE 방식)
//    @DeleteMapping("/delete/{reviewNo}")
//    public ResponseEntity<?> delete(@PathVariable long reviewNo) {
//
//        log.info("/review/{} : DELETE", reviewNo);
//
//        boolean remove = reviewService.getRemove(reviewNo);
//
////        if (remove == null) {
////            log.error("Failed to delete reviewNo: {}", reviewNo);
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body("리뷰 삭제에 실패.");
////        }
//
//        log.info("Successfully deleted reviewNo: {}", reviewNo);
//        return ResponseEntity.ok().body(remove);
//    }

    // 5. 좋아요 요청 비동기 처리
    @GetMapping("/like")
    @ResponseBody
    public ResponseEntity<?> like(long rno, HttpSession session) {
        log.info("like async request !");

        // 로그인한 유저만
        LoginUserInfoDto loginUser = (LoginUserInfoDto) session.getAttribute("login");
        String email = loginUser.getEmail();

        ReactionDto dto = likeLogService.like(rno, email); //좋아요 요청 처리

        return ResponseEntity.ok().body(dto);
    }

    // 4. 게시글 삭제 요청
//    @GetMapping("/delete")
//    public String delete(long reviewNo) {
//        System.out.println("/board/delete : GET");
//
//        reviewService.remove(reviewNo);
//        return "redirect:/reviewjsp/detail";
//    }
}
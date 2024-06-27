package com.project.mvc.controller.jihye;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.request.jihye.ReviewModifyDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.mapper.jihye.ReviewMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import com.project.mvc.service.jihye.ReviewService;
import com.project.mvc.service.zyo.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final MediaMapper mediaMapper;
//    private final UserService userService;

    // 1. 리뷰 목록 조회 요청 : GET
    @GetMapping("/list/{mno}")
    public String list (@PathVariable("mno") long mno, Model model, HttpSession session) {
//        if (mno == 0) {
//            String message = "글 번호는 0번이 될 수 없습니다.";
//            log.warn(message);
////            return ResponseEntity
////                    .badRequest()
////                    .body(message);
//        }

        log.info("/review/list {} : GET", mno);

        // 특정 미디어 정보 조회
        MediaDetailDto media = mediaService.detail(mno);

        // 리뷰 목록 조회
        ReviewListDto rList = reviewService.findList(mno); // 모든 미디어와 리뷰 목록 가져오기
        //        reviews.setLoginUser(LoginUtil.getLoggedInUser(session));

        model.addAttribute("media", media);
        model.addAttribute("reviews", rList);
//        return ResponseEntity
//                .ok()
//                .body(reviews);
        return "reviewjsp/detail";
    }

//    @GetMapping("/list/{mno}")
//    public String list(@PathVariable("mno") long mno, Model model) {
//        log.info("/review/{} : GET", mno);
//
//        // 특정 미디어 정보 조회
//        MediaDetailDto media = mediaService.detail(mno);
//
//        // 리뷰 목록 조회
//        ReviewListDto rList = reviewService.findList(mno); // 모든 미디어와 리뷰 목록 가져오기
//
//        model.addAttribute("media", media);
//        model.addAttribute("reviews", rList);
//
//        return "reviewjsp/detail";
//    }

    // 2. 리뷰 등록 요청
    // @RequestBody : 클라이언트가 전송한 데이터를 json으로 받아서 파싱
    @PostMapping("/add")
    //BindingResult 입력값 검증 결과 데이터를 갖고 있는 객체
    public ResponseEntity<?> addReview(@Validated @RequestBody ReviewPostDto dto, BindingResult result, HttpSession session) {
        log.info("/review : POST");
        log.debug("parameter - {}", dto);

//        dto.setNickname(dto.getNickname());

        if (result.hasErrors()) {
            log.warn("Validation errors: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body("Invalid input data");
        }

        boolean flag = reviewService.register(dto);
        if (flag) {
            log.info("Review successfully registered");
            ReviewListDto reviewListDto = reviewService.findList(dto.getMediaNo());
            return ResponseEntity.ok(reviewListDto);
        } else {
            log.warn("Failed to register review");
            return ResponseEntity.internalServerError().body("Failed to register review");
        }

//        return ResponseEntity
//                .ok()
//                .body(reviewService.findList(dto.getMediaNo()));
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


    // 4. 리뷰 삭제 처리 요청
    @DeleteMapping("/delete/{reviewNo}")
    public ResponseEntity<?> delete(@PathVariable long reviewNo) {
        log.info("/review/{} : DELETE", reviewNo);

        ReviewListDto dtoList = reviewService.remove(reviewNo);

        return ResponseEntity
                .ok()
                .body(dtoList);

    }
}
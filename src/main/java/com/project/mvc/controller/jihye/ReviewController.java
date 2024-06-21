package com.project.mvc.controller.jihye;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.jihye.MediaDetailDto;
import com.project.mvc.dto.request.jihye.ReviewPostDto;
import com.project.mvc.dto.response.jihye.ReviewDetailDto;
import com.project.mvc.dto.response.jihye.ReviewListDto;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.Review;
import com.project.mvc.service.jihye.ReviewModifyDto;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    // 1. 리뷰 목록 조회 요청 : GET
    @GetMapping("/list/{mno}")
    public String list(@PathVariable("mno") long mno, Model model) {
        log.info("/review/{} : GET", mno);

        // 특정 미디어 정보 조회
        MediaDetailDto media = mediaService.detail(mno);
        // 특정 영화 정보 조회 (예시로 하드코딩)
//        Media movie = new Media();
//        movie.setMediaNo(75);
//        movie.setCategoryNo(1);
//        movie.setMediaTitle("기생충");
//        movie.setCreator("봉준호");
//        movie.setMediaDesc("소름끼치는 ...");
//        movie.setRating(8);
//        movie.setCreatedAt(20230234);

        // 리뷰 목록 조회
        List<ReviewDetailDto> rList = reviewService.findList(mno); // 모든 미디어와 리뷰 목록 가져오기

        model.addAttribute("media", media);
        model.addAttribute("reviews", rList);

        return "reviewjsp/detail";
    }

    // 2. 리뷰 등록 요청
    // @RequestBody : 클라이언트가 전송한 데이터를 json으로 받아서 파싱
    @PostMapping("/add")
    //BindingResult 입력값 검증 결과 데이터를 갖고 있는 객체
    public ResponseEntity<?> addReview(@Validated @RequestBody ReviewPostDto dto, BindingResult result, Model model) {
        log.info("/review : POST");
        log.debug("parameter - {}", dto);

        if (result.hasErrors()) {
            // 유효성 검사 에러 처리
//            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.")
            Map<String, String> errors = makeValidationMessageMap(result);
            model.addAttribute("errors", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        boolean success = reviewService.register(dto);
        if (success) {
            List<ReviewDetailDto> updatedReviews = reviewService.findList(dto.getMediaNo());
            return ResponseEntity.ok(updatedReviews);
        } else {
            return ResponseEntity.internalServerError().body("리뷰 등록에 실패했습니다.");
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
    @PostMapping("/modify")
    public ResponseEntity<?> modifyReview(@Valid @RequestBody ReviewModifyDto dto, BindingResult result) {
        log.info("/review/modify : POST");
        log.debug("parameter - {}", dto);

        if (result.hasErrors()) {
            // 유효성 검사 에러 처리
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        boolean success = reviewService.modify(dto);
        if (success) {
            return ResponseEntity.ok("리뷰가 성공적으로 수정되었습니다.");
        } else {
            return ResponseEntity.internalServerError().body("리뷰 수정에 실패했습니다.");
        }
    }

    // 4. 리뷰 삭제 처리 요청
    @DeleteMapping("/{rno}")
    public ResponseEntity<?> delete(@PathVariable long rno) {
        log.info("/review/{} : DELETE", rno);

        List<ReviewDetailDto> updatedReviews = reviewService.remove(rno);

        return ResponseEntity
                .ok()
                .body(updatedReviews);
//        if (updatedReviews != null) {
//            return ResponseEntity.ok(updatedReviews);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }
}

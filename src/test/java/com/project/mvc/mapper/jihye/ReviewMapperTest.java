package com.project.mvc.mapper.jihye;

import com.project.mvc.entity.DiscussionStatus;
import com.project.mvc.entity.Review;
import com.project.mvc.mapper.zyo.MediaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReviewMapperTest {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    MediaMapper mediaMapper;

    @Test
    @DisplayName("테스트를 위해 리뷰 50개 랜덤으로 등록")
    void saveReviewTest() {

        Random random = new Random();

        for (int i = 1; i <= 10 ; i++) {
            // 10부터 81 사이의 랜덤한 미디어 번호 생성
            long mediaNo = random.nextInt(72) + 10; // 10 ~ 81 사이의 랜덤 번호

            Review r = new Review();
            r.setMediaNo(mediaNo);
            r.setReviewText("테스트 리뷰 내용" + i);
            r.setUserRating(random.nextDouble() * 10); // 0부터 10 사이의 랜덤 별점 설정
            r.setDiscussionStatus(DiscussionStatus.ALLOW);

//            boolean save = reviewMapper.saveReview(r);
//            assertEquals(true, save, "리뷰 등록 실패");r
        }
    }

    @Test
    @DisplayName("미디어 번호가 14번인 리뷰 전체 조회")
    void findAllTest() {
        //given
        long mediaNo = 14;
        //when
//        List<Review> r = reviewMapper.findAll(mediaNo);
        //then
//        assertNotNull(r);
//        r.forEach(System.out::println);
    }

    @Test
    @DisplayName("리뷰 번호가 177번인 것 삭제")
    void deleteTest() {
        //given
        long reviewNo = 177;
        //when
//        reviewMapper.deleteReview(reviewNo);
        //then
    }

    @Test
    @DisplayName("리뷰 번호가 168인 내용 텍스트 '리뷰 내용 수정'으로 변경하기")
    void updateTest() {
        //given
        long reviewNo = 168;
        //when
        Review r = Review.builder()
                .reviewNo(reviewNo)
                .reviewText("리뷰 내용 수정 테스트")
                .userRating(5)
                .discussionStatus(DiscussionStatus.DISALLOW)
                .build();
//        reviewMapper.updateReview(r);
        //then
    }

}

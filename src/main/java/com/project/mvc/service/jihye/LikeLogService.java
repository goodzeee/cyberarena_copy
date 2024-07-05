package com.project.mvc.service.jihye;

import com.project.mvc.dto.response.jihye.ReactionDto;
import com.project.mvc.entity.LikeLog;
import com.project.mvc.mapper.jihye.LikeLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeLogService {

    private final LikeLogMapper likeLogMapper;

     //좋아요 중간 처리
    public LikeLog handleReaction(long reviewNo, String email) {
        // 1. 현재 리뷰글에 특정 사용자가 좋아요를 했는지 확인
        LikeLog existingReaction = likeLogMapper.findOne(reviewNo, email);

        // 새 좋아요 리액션 객체
        LikeLog newReaction = LikeLog.builder()
                .email(email)
                .reviewNo(reviewNo)
                .build();

        // 리액션이 널이 아닌 경우 -> 이미 좋아요 리액션 함
        if (existingReaction != null) {
            // 이미 좋아요를 눌렀던 경우 -> 기존 좋아요 취소됨
            likeLogMapper.delete(reviewNo, email);
        } else {
            // 처음 좋아요를 누른 경우 -> 좋아요 추가됨
            likeLogMapper.save(newReaction);
        }
        // 좋아요 상태 리턴
        return likeLogMapper.findOne(reviewNo, email);
    }

    public ReactionDto like(long reviewNo, String email) {
        LikeLog likeLog = handleReaction(reviewNo, email);

        int likeCount = likeLogMapper.countLikes(reviewNo);
        String userReaction = likeLog != null ? "like" : null;

        return ReactionDto.builder()
                .likeCount(likeCount)
                .userReaction(userReaction)
                .build();
    }

    // 좋아요 수
    public int countLikes(Long reviewNo) {
        log.debug("Counting likes for Review No: {}", reviewNo);
        return likeLogMapper.countLikes(reviewNo);
    }


//
//    private ReactionDto getReactionDto(long reviewNo, LikeLog likeLog) {
//
//        String reactionType = "like";
//
//        if (likeLog != null) {
//            reactionType = String.valueOf(likeLog.getReviewLikeNo());
//        }
//
//        return ReactionDto.builder()
//                .likeCount(likeLogMapper.countLikes(reviewNo))
//                .userReaction(reactionType)
//                .build();
//    }
}

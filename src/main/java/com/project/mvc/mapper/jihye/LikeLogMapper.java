package com.project.mvc.mapper.jihye;

import com.project.mvc.entity.LikeLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeLogMapper {

    // 좋아요 생성 - 좋아요 처음 찍었을 때
    void save(LikeLog likeLog);

    // 좋아요 삭제 - 좋아요 취소했을 때
    void delete(@Param("reviewNo") long reviewNo,
                @Param("email") String email);

    // 좋아요 단일 조회 - 사용자가 특정 리뷰에 좋아요를 했는지 확인
    LikeLog findOne(@Param("reviewNo") long reviewNo, String email);

    // 특정 리뷰에 총 좋아요 수 조회
    int countLikes(long reviewNo);
}

package com.project.mvc.service.kibeom;


import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.mapper.kibeom.DiscussReplyMapper;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionReplyService {

    private final DiscussReplyMapper discussReplyMapper;


    public boolean insert(DiscussionCommentRequestDto dto) {
        boolean flag = discussReplyMapper.insert(dto);
        if (flag) {
            log.info("등록 성공 : {}", dto);
            return true;
        }
        return false;
    }

    // 날짜 포매팅
    public String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy. MM. dd a HH:mm");
        return regDateTime.format(pattern);
    }

    public int getCount(long dno) {
        int replyCount = discussReplyMapper.getReplyCountByDiscussionNo(dno);
        if (replyCount == 0) {
            return 0;
        } else {
            return replyCount;
        }
    }


    public boolean remove(long rno) {
        return  discussReplyMapper.remove(rno);
    }
}

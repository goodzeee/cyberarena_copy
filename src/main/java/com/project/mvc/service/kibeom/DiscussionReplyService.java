package com.project.mvc.service.kibeom;


import com.project.mvc.dto.request.kibeom.DiscussionCommentRequestDto;
import com.project.mvc.mapper.kibeom.DiscussReplyMapper;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionReplyService {

    private final DiscussReplyMapper discussReplyMapper;


    public void insert(DiscussionCommentRequestDto dto) {
        boolean flag = discussReplyMapper.insert(dto);
        if (flag) {
            log.info("등록 성공 : {}", dto);
        }
    }
}

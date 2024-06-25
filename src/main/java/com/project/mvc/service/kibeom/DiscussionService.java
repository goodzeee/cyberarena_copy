package com.project.mvc.service.kibeom;

import com.project.mvc.common.jihye.Page;
import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import com.project.mvc.dto.response.kibeom.DiscussionDetailResponseDto;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.User;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.seongjin.UserMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionService {

    private final DiscussionMapper discussionMapper;
    private final MediaMapper mediaMapper;
    private final UserMapper userMapper;

    public List<DiscussResponseDto> findAll(Page page) {
        List<DiscussFindAllDto> list = discussionMapper.findAll(page);
        List<DiscussResponseDto> dtoList = list.stream()
                .map(d -> new DiscussResponseDto(d))
                .collect(Collectors.toList());

        return dtoList;
    }


    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    public MakeDiscussionDto getMediaNo(MakeDiscussionDto dto) {
        List<Media> byTitle = mediaMapper.findAll(Search.builder()
                        .keyword(dto.getMediaName())
                        .searchType("title")
                .build());
        log.debug(byTitle.toString());
        // 지금은 미디어 이름만 가지고 있는데,
        // 미디어매퍼에서 이름으로 찾는 함수 만들어서
        // 번호 받아와야 함.
        // 받아옴, 이제 discumapper에서 insert.
        // (필드로 mediaNo 가지고 있어야함.)
        for (Media media : byTitle) {
            dto.setMediaNo(media.getMediaNo());
        }
//        dto.setMediaNo(byTitle.getMediaNo());
        return dto;
    }

    public DiscussionDetailResponseDto findOne(long discussionNo) {
        Discussion d = discussionMapper.findOne(discussionNo);
        DiscussionDetailResponseDto dto = new DiscussionDetailResponseDto(d);
        User foundEmail = userMapper.findOne(d.getEmail());
        dto.setNickname(foundEmail.getNickname());

        return dto;
    }


    public void updateViewCount(long dno) {
        discussionMapper.updateViewCount(dno);
    }

    // 페이지 생성 파라미터를 받아옴
    public int getCount() {
        return discussionMapper.count();
    }
}

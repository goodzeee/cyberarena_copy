package com.project.mvc.service.kibeom;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.dto.request.kibeom.DiscussionModifyDto;
import com.project.mvc.dto.request.kibeom.MakeDiscussionDto;
import com.project.mvc.dto.response.kibeom.*;
import com.project.mvc.dto.seongjin.DiscussMyPageDto;
import com.project.mvc.dto.seongjin.LoginUserInfoDto;
import com.project.mvc.entity.DiscussReply;
import com.project.mvc.entity.Discussion;
import com.project.mvc.entity.Media;
import com.project.mvc.entity.User;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import com.project.mvc.mapper.seongjin.UserMapper;
import com.project.mvc.mapper.zyo.MediaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public List<DiscussResponseDto> findAll(Search page) {
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

        Media media = mediaMapper.findMedia(d.getMediaNo());
        String mediaTitle = media.getMediaTitle();
        dto.setMediaTitle(mediaTitle);

        User foundEmail = userMapper.findOne(d.getEmail());
        dto.setNickname(foundEmail.getNickname());

        return dto;
    }


    public void updateViewCount(long dno) {
        discussionMapper.updateViewCount(dno);
    }

    // 페이지 생성 파라미터를 받아옴
    public int getCount(Search search) {
        return discussionMapper.count(search);
    }

    public boolean remove(long dno) {
        return discussionMapper.remove(dno);
    }

    public boolean modify(DiscussionModifyDto dto) {
        return discussionMapper.modify(dto);
    }

    public List<DiscussReplyResponseDto> convertToDto(List<DiscussReply> all) {
        List<DiscussReplyResponseDto> list = all.stream()
                .map(d -> new DiscussReplyResponseDto(d))
                .collect(Collectors.toList());

        return list;
    }

    public List<DiscussMyPageDto> findMyDiscuss(String email) {

        return discussionMapper.findByEmail(email).stream().map(dto -> {
            if (dto.getDiscussionTitle().length() > 9) {
                dto.setShortTitle(dto.getDiscussionTitle().substring(0, 8) + "...");
            } else {
                dto.setShortTitle(dto.getDiscussionTitle());
            }
            return dto;
        }).collect(Collectors.toList());
    }


    public List<DiscussAsideListDto> findAsideList() {
        return discussionMapper.findAsideList();
    }

    public List<DiscussResponseDto> getSortedDiscussions(String sort) {
        List<DiscussFindAllDto> list = discussionMapper.getSortedDiscussion(sort);
        List<DiscussResponseDto> dtoList = list.stream()
                .map(d -> new DiscussResponseDto(d))
                .collect(Collectors.toList());
        return dtoList;
    }
}

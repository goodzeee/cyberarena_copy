package com.project.mvc.service.kibeom;

import com.project.mvc.dto.response.kibeom.DiscussFindAllDto;
import com.project.mvc.dto.response.kibeom.DiscussResponseDto;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionService {

    private final DiscussionMapper discussionMapper;

    public List<DiscussResponseDto> findAll() {
        List<DiscussFindAllDto> list = discussionMapper.findAll();
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

}

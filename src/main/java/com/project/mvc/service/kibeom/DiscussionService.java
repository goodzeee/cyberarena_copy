package com.project.mvc.service.kibeom;

import com.project.mvc.entity.Discussion;
import com.project.mvc.mapper.kibeom.DiscussionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionService {

    private final DiscussionMapper discussionMapper;

    public List<Discussion> findAll() {
        return discussionMapper.findAll();
    }
}

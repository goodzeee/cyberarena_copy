package com.project.mvc.service.zyo;

import com.project.mvc.common.zyo.Search;
import com.project.mvc.entity.Media;
import com.project.mvc.mapper.MediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final MediaMapper mediaMapper;

    // 미디어 조회
    public List<Media> findList(Search media) {
        return mediaMapper.findAll(media);
    }

    // 상세 조회
    public Media detail(long mediaNo) {
        // 게시물 정보 조회
        return mediaMapper.findOne(mediaNo);
    }
}

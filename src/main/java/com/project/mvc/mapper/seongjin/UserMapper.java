package com.project.mvc.mapper.seongjin;

import com.project.mvc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 회원가입 유저 정보 저장요청
    boolean save(User user);

    // 이메일로 유저 정보 조회요청
    User findOne(String email);

    // 이메일 혹은 아이디 중복여부 확인
    boolean isExist(@Param("type") String type, @Param("keyword") String keyword);


}

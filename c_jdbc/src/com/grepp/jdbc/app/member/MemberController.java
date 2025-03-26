package com.grepp.jdbc.app.member;

// NOTE 01 MVC
// MVC 패턴 : Model(Service, Dao), Controller, View 로 소프트웨어 구조를 구성하는 패턴
// Controller (Presentation Layer)
// 클라이언트와 직접 상호작용하는 Layer
// 핵심로직인 Model 이 외부에 종속되지 않도록 분리하기 위해 Client 와 직접 상호작용하는 Presentation Layer

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grepp.jdbc.app.member.dto.MemberDto;
import com.grepp.jdbc.app.member.validator.MemberValidator;

// 1. 사용자의 입력값을 어플리케이션 내에서 사용하기 적합한 형태로 파싱
// 2. 요청에 대해 인가 처리를 하는 외벽 역할
// 3. Client 에게 비지니스로직의 결과물을 어떤 형태(text/html, json) 로 보여줄 것인지 선택
public class MemberController {

    private final MemberValidator validator = new MemberValidator();
    private final MemberService memberService = new MemberService();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String signup(MemberDto memberDto) {
        validator.validate(memberDto);
        return gson.toJson(memberService.signup(memberDto));
    }

    public String get(String userId) {
        return gson.toJson(memberService.selectById(userId));

    }

    public String getAll() {
        return gson.toJson(memberService.selectAll());
    }
}

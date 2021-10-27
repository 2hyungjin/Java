package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.List;

public interface MemberService {
    public void registerMember(Member member);

    public boolean isEmailDuplicated(String email);

    public List<Member> getList();}

package com.hello.core.member;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@Getter
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // -> ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트로 만듬
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }
}

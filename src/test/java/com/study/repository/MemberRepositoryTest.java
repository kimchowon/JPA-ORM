package com.study.repository;

import com.study.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 객체 등록 테스트")
    void insertMemberTest() {
        // given
        Member member = Member.builder().username("현호").age(25).build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        assertEquals(saveMember.getUsername(), "현호");
    }

    @Test
    @DisplayName("회원 객체 조회 테스트")
    void selectMemberTest() {
        // given - nothing

        // when
        Optional<Member> member = memberRepository.findById("id1");

        // then
        assertEquals(member.get().getUsername(), "초원");
    }

    @Test
    @DisplayName("회원 객체 수정 테스트")
    void updateMemberTest() {
        // given - nothing

        // when
        Optional<Member> optionalMember = memberRepository.findById("id1");
        Member member = optionalMember.get();
        member.setAge(29);
        Member saveMember = memberRepository.save(member);

        assertEquals(saveMember.getAge(), 29);
    }

    @Test
    @DisplayName("회원 객체 삭제 테스트")
    void deleteMemberTest() {
        // given

        // when
        Optional<Member> optionalMember = memberRepository.findById("id1");
        Member member = optionalMember.get();

        memberRepository.delete(member);

        optionalMember = memberRepository.findById("id1");
        assertFalse(optionalMember.isPresent());
    }
}
package com.study.repository;

import com.study.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMemberTest() {
        Member member = Member.builder().id("id3").username("유진").age(27).build();
        Member saveMember = memberRepository.save(member);
        assertEquals(saveMember.getUsername(), "유진");
    }
}
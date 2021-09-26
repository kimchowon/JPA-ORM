package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch05Member;
import com.study.chapter05.entity.Ch05Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
@DisplayName("단방향 연관관계 매핑 테스트")
class OneWayMappingTest {

    @Autowired
    private Ch05TeamRepository teamRepository;

    @Autowired
    private Ch05MemberRepository memberRepository;

    @Test
    @DisplayName("등록")
    void insertTest() {
        // 팀1 저장
        Ch05Team team1 = new Ch05Team("team1", "팀1");
        teamRepository.save(team1);

        // 회원1 저장
        Ch05Member member1 = new Ch05Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정: 회원1 -> 팀1
        memberRepository.save(member1);

        // 회원2 저장
        Ch05Member member2 = new Ch05Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정: 회원2 -> 팀1
        memberRepository.save(member2);
    }

    @Test
    @DisplayName("조회")
    void selectTest() {
        Optional<Ch05Member> optionalCh05Member = memberRepository.findById("member1");

        if (optionalCh05Member.isPresent()) {
            Ch05Member member = optionalCh05Member.get();
            Ch05Team team = member.getTeam();
            log.debug("조회 성공!, team name = {}", team.getName());
        } else {
            log.debug("조회 실패!, 회원 정보 비어 있음");
        }
    }

    @Test
    @DisplayName("수정")
    void updateTest() {
        // 새로운 팀2
        Ch05Team team2 = new Ch05Team("team2", "팀2");
        teamRepository.save(team2);

        // 회원1에 새로운 팀2 설정
        Optional<Ch05Member> optionalCh05Member = memberRepository.findById("member1");
        if (optionalCh05Member.isPresent()) {
            Ch05Member member = optionalCh05Member.get();
            member.setTeam(team2);

            memberRepository.save(member);

            log.debug("회원 정보 업데이트 성공");
        } else {
            log.debug("회원 정보 비어 있음");
        }
    }

    @Test
    @DisplayName("연관관계 제거")
    void deleteTest() {
        Optional<Ch05Member> optionalCh05Member = memberRepository.findById("member1");

        if (optionalCh05Member.isPresent()) {
            Ch05Member member = optionalCh05Member.get();
            member.setTeam(null); // 연관관계 제거

            memberRepository.save(member);
            log.debug("연관관계 제거 성공");
        } else {
            log.debug("회원 정보 비어 있음");
        }
    }
}
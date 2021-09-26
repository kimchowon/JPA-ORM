package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch05Member;
import com.study.chapter05.entity.Ch05Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@DisplayName("양방향 연관관계 매핑 테스트")
public class TwoWayMappingTest {
    @Autowired
    private Ch05TeamRepository teamRepository;

    @Autowired
    private Ch05MemberRepository memberRepository;

    @BeforeEach
    void init() {
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
    @Transactional(readOnly = true) // 지연 조회 시점까지 세션을 유지
    @DisplayName("팀에 속한 회원들 조회")
    void selectTest() {
        log.debug("팀 정보 조회");
        Optional<Ch05Team> optionalCh05Team = teamRepository.findById("team1");

        if (optionalCh05Team.isPresent()) {
            log.debug("회원들 정보 조회");
            Ch05Team team = optionalCh05Team.get();

            List<Ch05Member> memberList = team.getMemberList();
            for (Ch05Member member : memberList) {
                log.debug("회원 = {}", member.getUsername());
            }
        } else {
            log.debug("팀 정보 비어 있음.");
        }
    }

    @Test
    @DisplayName("회원을 새로운 팀에 소속되도록 수정")
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
            log.debug("회원 정보 수정 성공");
        } else {
            log.debug("회원 정보 비어 있음.");
        }
    }

    @Test
    @DisplayName("회원1을 팀에 소속되지 않도록 연관관계 제거")
    void deleteTest() {
        Optional<Ch05Member> optionalCh05Member = memberRepository.findById("member1");

        if (optionalCh05Member.isPresent()) {
            Ch05Member member = optionalCh05Member.get();
            member.setTeam(null);
            memberRepository.save(member);
            log.debug("연관관계 제거 성공.");
        } else {
            log.debug("회원 정보 비어 있음.");
        }
    }

    @Test
    @DisplayName("팀 정보 삭제 - 회원 정보와 매핑되어 있는 경우")
    void deleteMappedEntityTest() {
        // 연관된 엔티티를 삭제하려면 기존에 있던 연관관계를 모두 제거하고 삭제해야 함.
        // 그렇지 않으면 외래키 제약조건으로 인해 데이터베이스에서 오류 발생
        Optional<Ch05Team> optionalCh05Team = teamRepository.findById("team1");

        if (optionalCh05Team.isPresent()) {
            Ch05Team team = optionalCh05Team.get();

            // 삭제 직전에 Ch05Team의 dissmissMember 메소드 실행하여 팀과 회원의 연관관계 제거
            teamRepository.delete(team);

            log.debug("팀 정보 삭제 성공.");
        } else {
            log.debug("팀 정보 비어 있음.");
        }
    }
}

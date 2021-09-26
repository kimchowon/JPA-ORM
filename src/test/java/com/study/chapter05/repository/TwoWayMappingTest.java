package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch05Member;
import com.study.chapter05.entity.Ch05Team;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    @Transactional(readOnly = true) // 지연 조회 시점까지 세션을 유지
    @DisplayName("팀에 속한 회원들 조회")
    void selectTest() {
        Optional<Ch05Team> optionalCh05Team = teamRepository.findById("team1");

        if (optionalCh05Team.isPresent()) {
            Ch05Team team = optionalCh05Team.get();

            List<Ch05Member> memberList = team.getMemberList();
            for (Ch05Member member : memberList) {
                log.debug("회원 = {}", member.getUsername());
            }
        }else {
            log.debug("팀 정보 is null");
        }
    }
}

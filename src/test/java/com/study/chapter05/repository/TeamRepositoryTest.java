package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch03Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @DisplayName("팀1 저장")
    void insertTeamTest() {
        Ch03Team team1 = new Ch03Team("team1", "팀1");
        Ch03Team saveTeam = teamRepository.save(team1);

        assertEquals(saveTeam.getName(), "팀1");
    }
}
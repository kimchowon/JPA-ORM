package com.study.chapter05.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ch05_TEAM")
public class Ch05Team {

    @Id
    @Column(name = "TEAM_ID") // 외래키 매핑
    private String id;

    private String name;

    @OneToMany(mappedBy = "team") // 양방향 연관관계 설정
    private List<Ch05Member> memberList = new ArrayList<>();

    public Ch05Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 팀 정보를 영속성 컨텍스트에서 삭제하기 직전에 호출
     * 팀과 회원의 연관관계를 제거
     */
    @PreRemove
    public void dismissMember() {
        for (Ch05Member member : memberList) {
            member.setTeam(null);
        }
    }
}

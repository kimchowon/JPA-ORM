package com.study.chapter05.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Team {

    @Id
    @Column(name = "TEAM_ID") // 외래키 매핑
    private String id;

    private String name;

    @OneToMany
    List<Member> memberList;
}

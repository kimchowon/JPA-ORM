package com.study.chapter05.entity;

import javax.persistence.*;

@Entity
@Table(name = "Ch03_MEMBER")
public class Ch03Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Ch03Team team;
}

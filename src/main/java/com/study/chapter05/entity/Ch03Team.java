package com.study.chapter05.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ch03_TEAM")
public class Ch03Team {

    @Id
    @Column(name = "TEAM_ID") // 외래키 매핑
    private String id;

    private String name;
}

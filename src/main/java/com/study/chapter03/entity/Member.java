package com.study.chapter03.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 추가
    private String id;

    @Column(name = "NAME")
    private String username;

    private int age;
}

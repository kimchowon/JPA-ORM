package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch05Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ch05MemberRepository extends JpaRepository<Ch05Member, String> {
}

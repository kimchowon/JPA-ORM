package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch03Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Ch03Team, String> {
}

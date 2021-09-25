package com.study.chapter05.repository;

import com.study.chapter05.entity.Ch05Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ch05TeamRepository extends JpaRepository<Ch05Team, String> {
}

package com.backend.repositories;

import com.backend.entity.RefCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefcodeRepository extends JpaRepository<RefCode, Long> {

    RefCode findByRefCode(String refCode);

    List<RefCode> findByCategory(String category);

}

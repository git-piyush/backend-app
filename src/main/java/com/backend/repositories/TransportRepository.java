package com.backend.repositories;

import com.backend.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
}

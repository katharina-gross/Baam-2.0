package com.example.baam2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.baam2.model.SessionModel;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionModel, Long> {
}
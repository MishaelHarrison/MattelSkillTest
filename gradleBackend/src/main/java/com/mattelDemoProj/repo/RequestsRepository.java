package com.mattelDemoProj.repo;

import com.mattelDemoProj.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface RequestsRepository extends JpaRepository<Request, Integer> {
    long deleteByBy(int by);
    void deleteByExpirationIsLessThan(Timestamp expiration);
}

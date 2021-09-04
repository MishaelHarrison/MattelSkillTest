package com.mattelDemoProj.repo;

import com.mattelDemoProj.models.Grants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrantsRepository extends JpaRepository<Grants, Integer> {
    Optional<Grants> findByTo(int to);
}

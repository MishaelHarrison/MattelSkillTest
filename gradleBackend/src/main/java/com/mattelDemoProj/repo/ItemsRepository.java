package com.mattelDemoProj.repo;

import com.mattelDemoProj.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
}

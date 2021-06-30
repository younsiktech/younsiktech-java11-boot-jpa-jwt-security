package com.tech.younsik.repository;

import com.tech.younsik.entity.Purchase;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String>, AdminPurchaseRepository {
    
    List<Purchase> findByUserId(Long userId);
    
    List<Purchase> findByIdIn(List<String> ids);
    
}

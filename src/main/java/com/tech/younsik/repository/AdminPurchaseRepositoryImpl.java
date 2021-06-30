package com.tech.younsik.repository;

import com.tech.younsik.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AdminPurchaseRepositoryImpl implements AdminPurchaseRepository {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public List<User> adminUserSearch(List<Long> userIds) throws Exception {
        String query = "SELECT p.user_id, MAX(p.payment_date) as payment_date FROM "
            + "(SELECT * FROM purchases WHERE user_id in (1,2) ORDER BY payment_date desc) as p GROUP BY p.user_id;";
        return null;
    }
}

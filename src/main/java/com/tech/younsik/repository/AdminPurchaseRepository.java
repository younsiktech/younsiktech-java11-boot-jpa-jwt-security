package com.tech.younsik.repository;

import com.tech.younsik.entity.User;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface AdminPurchaseRepository {
    List<User> adminUserSearch(List<Long> userIds) throws Exception;
}

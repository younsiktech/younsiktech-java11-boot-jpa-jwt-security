package com.tech.younsik.service;

import com.tech.younsik.constant.OrderConst.Status;
import com.tech.younsik.dto.object.PurchaseObject;
import com.tech.younsik.dto.object.UserObject;
import com.tech.younsik.dto.response.PagingResponse;
import com.tech.younsik.entity.Purchase;
import com.tech.younsik.entity.User;
import com.tech.younsik.exception.UserException;
import com.tech.younsik.exception.UserException.Type;
import com.tech.younsik.repository.PurchaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PurchaseService {
    
    private final PurchaseRepository purchaseRepository;
    
    @Autowired
    private UserService userService;
    
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    
    @Transactional
    public PurchaseObject createPurchase(PurchaseObject purchaseObject, String currentUser) {
        Optional<User> optionalUser = userService.findUserByEmail(currentUser);
        
        if (optionalUser.isEmpty()) {
            log.error("User not exist");
            throw new UserException("User not exist", Type.USER_NOT_FOUND);
        }
        
        User user = optionalUser.get();
        
        Purchase purchase = purchaseRepository.saveAndFlush(
            Purchase.builder()
                .userId(user.getId())
                .name(purchaseObject.getName())
                .status(Status.PURCHASED)
                .build());
        
        return purchase.toObject();
    }
    
    @Transactional(readOnly = true)
    public PagingResponse<PurchaseObject> showPurchases(PurchaseObject purchaseObject,
        String currentUser,
        PageRequest pageRequest) {
        UserObject userObject = userService.selectUser(purchaseObject.getUserId(), currentUser);
        
        List<String> purchaseIds = findPurchaseIdsByUserId(userObject.getId());
        
        List<String> pagePurchaseIds = purchaseIds.stream()
            .skip(Math.max(0, (pageRequest.getPageNumber()) * pageRequest.getPageSize()))
            .limit(pageRequest.getPageSize())
            .collect(Collectors.toList());
        
        Page<String> page = new PageImpl<>(pagePurchaseIds, pageRequest, pagePurchaseIds.size());
        
        if (pagePurchaseIds.size() == 0) {
            return new PagingResponse<>(page, new ArrayList<>());
        }
        
        List<Purchase> purchases = purchaseRepository.findByIdIn(pagePurchaseIds);
        
        return new PagingResponse<>(page, toObjectList(purchases));
    }
    
    private List<String> findPurchaseIdsByUserId(Long userId) {
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);
        return purchases.stream().map(Purchase::getId).collect(Collectors.toList());
    }
    
    private List<PurchaseObject> toObjectList(List<Purchase> list) {
        return list.stream().map(Purchase::toObject).collect(Collectors.toList());
    }
}

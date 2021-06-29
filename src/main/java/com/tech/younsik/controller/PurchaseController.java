package com.tech.younsik.controller;

import com.tech.younsik.dto.object.PurchaseObject;
import com.tech.younsik.dto.request.PagingRequest;
import com.tech.younsik.dto.request.PurchaseRequest;
import com.tech.younsik.dto.response.PagingResponse;
import com.tech.younsik.dto.response.PurchaseResponse;
import com.tech.younsik.exception.InvalidParameterException;
import com.tech.younsik.service.PurchaseService;
import com.tech.younsik.util.SecurityUtils;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("controller.PurchaseController")
@RequestMapping("/purchase-api")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(path = "/v1/purchase")
    public PurchaseResponse createPurchaseV1(@Valid @RequestBody PurchaseRequest purchaseRequest,
        BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        Optional<String> optionalCurrentUser = SecurityUtils.getCurrentUsername();

        if(optionalCurrentUser.isEmpty()) {
            throw new RuntimeException("");
        }

        return create(purchaseRequest, optionalCurrentUser.get());
    }

    private PurchaseResponse create(PurchaseRequest purchaseRequest, String currentUser) {
        PurchaseObject purchaseObject = purchaseRequest.toObject();
        purchaseObject.setEmail(currentUser);
        return new PurchaseResponse(purchaseService.createPurchase(purchaseObject));
    }

    @GetMapping(path = "/v1/user/{userId}/purchases")
    public PagingResponse<PurchaseObject> showUserPurchasesV1(@PathVariable Long userId,
        @Valid PagingRequest request) {

        Optional<String> optionalCurrentUser = SecurityUtils.getCurrentUsername();

        if(optionalCurrentUser.isEmpty()) {
            throw new RuntimeException("");
        }

        return showUserPurchases(userId, optionalCurrentUser.get(), request.toPageRequest());

    }

    private PagingResponse<PurchaseObject> showUserPurchases(Long userId, String email, PageRequest pageRequest) {
        PurchaseObject purchaseObject = PurchaseObject.builder().userId(userId).email(email).build();
        return purchaseService.showPurchases(purchaseObject, pageRequest);
    }

}

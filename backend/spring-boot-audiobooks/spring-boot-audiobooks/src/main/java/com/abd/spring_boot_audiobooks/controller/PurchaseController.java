package com.abd.spring_boot_audiobooks.controller;

import com.abd.spring_boot_audiobooks.model.Purchase;
import com.abd.spring_boot_audiobooks.security.UserPrincipal;
import com.abd.spring_boot_audiobooks.service.AudiobookService;
import com.abd.spring_boot_audiobooks.service.IPurchaseService;
import com.abd.spring_boot_audiobooks.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase-history")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;


    @PostMapping
    public ResponseEntity<?> savePurchase(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<?> getAllPurchases(@AuthenticationPrincipal UserPrincipal userPrincipal){

        return new ResponseEntity<>(purchaseService.findAllPurchasedItemsOfUser(userPrincipal.getId()), HttpStatus.OK);

    }
}

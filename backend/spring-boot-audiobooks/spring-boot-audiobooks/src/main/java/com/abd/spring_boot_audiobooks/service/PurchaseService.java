package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.Purchase;
import com.abd.spring_boot_audiobooks.repository.IPurchaseRepository;
import com.abd.spring_boot_audiobooks.repository.projection.IPurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.sql.DriverManager.println;


@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Override
    public Purchase savePurchase(Purchase purchase){

        purchase.setPurchaseTime(LocalDateTime.now());

        System.out.println("Audiobook ID: " + purchase.getAudiobookId());

        return purchaseRepository.save(purchase);

    }

    @Override
    public List<IPurchaseItem> findAllPurchasedItemsOfUser(int userId){

        return purchaseRepository.findAllPurchasesOfUser(userId);

    }



}

package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.Purchase;
import com.abd.spring_boot_audiobooks.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseService {
    Purchase savePurchase(Purchase purchase);

    List<IPurchaseItem> findAllPurchasedItemsOfUser(int userId);
}

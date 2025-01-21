package com.abd.spring_boot_audiobooks.repository.projection;

import java.time.LocalDateTime;

public interface IPurchaseItem {

    String getTitle();
    Double getPrice();

    LocalDateTime getPurchaseTime();

}

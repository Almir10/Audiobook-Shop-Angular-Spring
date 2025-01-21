package com.abd.spring_boot_audiobooks.repository;

import com.abd.spring_boot_audiobooks.model.Purchase;
import com.abd.spring_boot_audiobooks.repository.projection.IPurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Integer> {


    @Query("select " +
            "ab.title as title, p.price as price, p.purchaseTime as purchaseTime " +
            "from Purchase p left join Audiobook ab on ab.Id = p.audiobookId " +
            "where p.userId = :userId")
    List<IPurchaseItem> findAllPurchasesOfUser(@Param("userId") int userId);

}

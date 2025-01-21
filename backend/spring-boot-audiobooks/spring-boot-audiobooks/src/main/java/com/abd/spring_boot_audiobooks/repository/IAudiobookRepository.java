package com.abd.spring_boot_audiobooks.repository;

import com.abd.spring_boot_audiobooks.model.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface IAudiobookRepository extends JpaRepository<Audiobook, Integer> {

    

}

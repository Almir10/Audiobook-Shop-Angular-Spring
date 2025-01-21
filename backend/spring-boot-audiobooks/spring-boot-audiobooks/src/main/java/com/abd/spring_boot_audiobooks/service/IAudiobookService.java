package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.Audiobook;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IAudiobookService {
    Audiobook saveAudiobook(Audiobook audiobook);

    @Transactional
    void deleteAudiobook(int Id);

    List<Audiobook> findAllAudiobooks();
}

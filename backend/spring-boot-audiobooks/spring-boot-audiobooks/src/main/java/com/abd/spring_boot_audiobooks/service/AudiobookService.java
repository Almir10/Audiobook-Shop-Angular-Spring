package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.Audiobook;
import com.abd.spring_boot_audiobooks.repository.IAudiobookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AudiobookService implements IAudiobookService{


    @Autowired
    private IAudiobookRepository audiobookRepository;


    @Override
    public Audiobook saveAudiobook(Audiobook audiobook){
        audiobook.setCreateTime(LocalDateTime.now());

        return audiobookRepository.save(audiobook);
    }

    @Override
    public void deleteAudiobook(int Id){

        audiobookRepository.deleteById(Id); //Delete by Id is transactional by itself so it doesnt require special annotation

    }

    @Override
    public List<Audiobook> findAllAudiobooks(){

        return audiobookRepository.findAll();

    }

}

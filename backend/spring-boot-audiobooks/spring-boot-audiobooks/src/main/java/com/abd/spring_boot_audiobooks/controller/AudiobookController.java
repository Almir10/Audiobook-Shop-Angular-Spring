package com.abd.spring_boot_audiobooks.controller;

import com.abd.spring_boot_audiobooks.model.Audiobook;
import com.abd.spring_boot_audiobooks.service.IAudiobookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/audiobook")
public class AudiobookController {

    @Autowired
    private IAudiobookService audiobookService;


    @PostMapping()
    public ResponseEntity<?> saveAudiobook(@RequestBody Audiobook audiobook){

        return new ResponseEntity<>(audiobookService.saveAudiobook(audiobook), HttpStatus.CREATED);

    }


    @DeleteMapping("{audiobookId}")
    public ResponseEntity<?> deleteAudiobook(@PathVariable Integer audiobookId){
        audiobookService.deleteAudiobook(audiobookId); // Ensure this handles the deletion
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<?> getAllAudiobooks(){

        return new ResponseEntity<>(audiobookService.findAllAudiobooks(), HttpStatus.OK);
    }



}

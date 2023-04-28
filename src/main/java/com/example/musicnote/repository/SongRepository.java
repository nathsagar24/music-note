package com.example.musicnote.repository;

import com.example.musicnote.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {

    boolean existsSongByFileNameEquals(String fileName);
    boolean existsSongByTitleEquals(String title);
}

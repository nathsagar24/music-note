package com.example.musicnote;

import com.example.musicnote.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MusicNoteApplication {

	@Autowired
	private static StorageService storageService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MusicNoteApplication.class, args);
		StorageService storageService = context.getBean(StorageService.class);

		System.out.println(storageService.getSongFileNames());
	}

}

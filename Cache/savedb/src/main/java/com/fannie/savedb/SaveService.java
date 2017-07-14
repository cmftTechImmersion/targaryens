package com.fannie.savedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SaveService {

	@Autowired
	private BooksRepository booksRepository;
	
	@RequestMapping(value="save", method=RequestMethod.GET, produces={"application/json"})
	public String saveBook(@RequestParam String title, @RequestParam String isbn ){
		
		saveBookInDb(title, isbn);
		
		System.out.println("Saving book in DB");
		
		return title;
	}
	
	private void saveBookInDb(String title, String isbn){
		
		
		Book newBook = new Book(title, isbn);
		booksRepository.insert(newBook);
		
		System.out.println("Saved");
		
	}
	
	
}

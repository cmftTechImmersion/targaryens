package com.fannie.savedb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Book, String> {

	public Book findByTitle(String title);
	//public Book saveBook(String title, String isbn);
	
}

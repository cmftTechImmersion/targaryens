package com.fannie.cache;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Book, String> {

	public Book findByTitle(String title);
	
}

package com.fannie.savedb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

//@Document(collection="books")
@Component
public class Book {
	
	@Id
	String isbn = "";
	String title = "";
	
	public Book(){
		
	}
	
	public Book(String isbn, String title) {
		super();
		
		this.title = title;
		this.isbn = isbn;
		
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		
		return isbn + " " + title;
	}

	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

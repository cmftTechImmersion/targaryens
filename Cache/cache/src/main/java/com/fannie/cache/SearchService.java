package com.fannie.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.spy.memcached.MemcachedClient;

@RestController
public class SearchService {

	@Autowired
	private MemcachedClient memcachedClient;
	
	@Autowired
	private BooksRepository booksRepository;
	
	@RequestMapping(value="search", method=RequestMethod.GET, produces={"application/json"})
	public String searchBooks(@RequestParam String keyword, @RequestParam String isbn){
		String bookInfo = (String) memcachedClient.get(keyword);
		
		if (bookInfo == null) {
			//go to mongodb and return the book details
			
			String bookFromDB ="";
			if (findinDb(keyword, isbn)) {
				bookFromDB = keyword;
			}
			//String bookFromDB = null;
			
			//insert book into memcached cache
			memcachedClient.set(keyword, 560000, bookFromDB);
			bookInfo = bookFromDB;
			System.out.println("Memcached cache lookup :: Miss");
		} else System.out.println("Memcached cache lookup :: Hit");
		
		return bookInfo;
	}
	
	private boolean findinDb(String title, String isbn){
		boolean ifFound = false;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		if (booksRepository.findByTitle(title) != null){
			ifFound= true;
		} else {
			params.set("title", title);
		    params.set("isbn", isbn);
			
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:9003/save?title=" + title + "&isbn=" + isbn;
			System.out.println("URL " + url);
			
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			
			
		}
		
		return ifFound;
		
		
	}
	
	
}

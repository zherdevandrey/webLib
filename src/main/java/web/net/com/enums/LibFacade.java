package web.net.com.enums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.net.com.BookDao;
import web.net.com.entities.Author;
import web.net.com.entities.Book;

@Component("libFacade")
public class LibFacade {
	@Autowired
	BookDao bookDao;
	
	
	List<Book> bookList;
	
	@Autowired
	SearchCriteria searchCriteria;
	
	public BookDao getBookDao() {
		return bookDao;
	}
	
	public void getBookByLetter() {
		bookList=  bookDao.getBookList(searchCriteria.getLetter());
	}
	
	public void  getBookByGenre() {
		bookList=  bookDao.getBookList(searchCriteria.getGenre());
	}
	
    public void searchBooksByText() {

        switch (searchCriteria.getSearchType()){
            case TITLE:
            	bookList = bookDao.getBookList(searchCriteria.getText());
                break;
            case AUTHOR:
            	bookList = bookDao.getBookList(new Author(searchCriteria.getText()));
                break;
        }

    }	

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
		bookList = bookDao.getBookList();
	}
	
	public List<Book> getBookList() {
		if(bookList==null) {
		bookList = bookDao.getBookList();
		}
		return bookList;
	}
	
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public LibFacade() {
	}
	
}

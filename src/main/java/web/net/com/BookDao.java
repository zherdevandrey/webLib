package web.net.com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import web.net.com.entities.Author;
import web.net.com.entities.Book;
import web.net.com.entities.Genre;
import web.net.com.entities.Publisher;
import web.net.com.enums.SearchCriteria;
import web.net.com.enums.SearchType;

@Component
public class BookDao {
	
	List<Book>bookList;
	
	@Autowired
	SessionFactory sessionFactory;
	DetachedCriteria detachedCriteria;
	ProjectionList prjList;
    SearchCriteria searchCriteria;

	public BookDao() {
		prjList = Projections.projectionList();
		prjList.add(Projections.property("id"),"id");
		prjList.add(Projections.property("name"),"name");
		prjList.add(Projections.property("image"),"image");
		prjList.add(Projections.property("genre"),"genre");
		prjList.add(Projections.property("publisher"),"publisher");
		prjList.add(Projections.property("author"),"author");
		prjList.add(Projections.property("pageCount"),"pageCount");
		prjList.add(Projections.property("isbn"),"isbn");
		prjList.add(Projections.property("publishYear"),"publishYear");
		prjList.add(Projections.property("descr"),"descr");
		prjList.add(Projections.property("rating"),"rating");
		prjList.add(Projections.property("voteCount"),"voteCount");
	}
	@Transactional
	public List<Book> getBookList() {
		return createBookLists(detachedCriteria).list();
	}
	
	@Transactional
	public List<Book> getBookList(Character letter) {
		bookList= createBookLists(detachedCriteria).add(Restrictions.ilike("b.name", letter+"%")).list();
		return bookList;
	}
	
	@Transactional
	public List<Book> getBookList(Genre genre) {
		bookList= createBookLists(detachedCriteria).add(Restrictions.eq("genre.id", genre.getId())).list();
		return bookList;
	}	
	
	@Transactional
	public List<Book> getBookList(String text) {
		bookList= createBookLists(detachedCriteria).add(Restrictions.ilike("b.name", text+"%")).list();
		return bookList;
	}	

	@Transactional
	public List<Book> getBookList(Author author) {
		bookList= createBookLists(detachedCriteria).add(Restrictions.ilike("author.fio", author.getFio())).list();
		return bookList;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public void creatAlias(DetachedCriteria detachedCriteria) {
		detachedCriteria.createAlias("b.author", "author");
		detachedCriteria.createAlias("b.publisher", "publisher");
		detachedCriteria.createAlias("b.genre", "genre");
	}

	@Transactional
    public Criteria createBookLists(DetachedCriteria detachedCriteria) {
		detachedCriteria = DetachedCriteria.forClass(Book.class,"b");
		creatAlias(detachedCriteria);
		Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.openSession());
		criteria.setProjection(prjList);
		criteria.setResultTransformer(Transformers.aliasToBean(Book.class));
		return criteria;
	}

}

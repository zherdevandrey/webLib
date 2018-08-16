package web.net.com;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import web.net.com.entities.Genre;

@Component
public class GenreDao {

	List<Genre>genreList;
	@Autowired
	SessionFactory sessionFactory;
	public GenreDao() {
	}
	@Transactional
	public List<Genre> getGenreList() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Genre.class);
		System.out.println("size!!!!!!"+criteria.list().size());
		return criteria.list();
	}
	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}
	
	
}

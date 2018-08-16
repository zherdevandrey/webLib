package web.net.com.enums;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import web.net.com.entities.Genre;



@Component
@Scope("singleton")
public class SearchCriteria{
    private String text;
    private SearchType searchType = SearchType.TITLE;
    private Character letter;
    private Genre genre;

    
    
    public Genre getGenre() {
		return genre;
	}

    
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }


	

}

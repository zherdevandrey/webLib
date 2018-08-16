package web.net.com.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
@Component
public class Utils {
	

private Map<String, SearchType> searchTypeList = new HashMap<String, SearchType>();

public Utils() {
}

@Autowired
private MessageSource msg;

private Character[] letters = new Character[]{'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�'};

public Character[] getLetters() {
    return letters;
}

public Map<String, SearchType> getSearchTypeList() {
    searchTypeList.clear();
    searchTypeList.put(msg.getMessage("author_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.AUTHOR);
    searchTypeList.put(msg.getMessage("book_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.TITLE);
    return searchTypeList;
}



public void setSearchTypeList(Map<String, SearchType> searchTypeList) {
	this.searchTypeList = searchTypeList;
}



}
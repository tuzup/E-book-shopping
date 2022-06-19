package com.cognizant.ebook.test;




import java.sql.Date;

import org.junit.Test;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;
import com.cognizant.ebook.model.Book;

public class AdminTestCase {

	@Test
	public void testEditBookDetails() {
	BookDao bookDao = new BookDaoSqlImpl();
	String str="2017-09-08";
	Date date=Date.valueOf(str);
	Book book = new Book((long)5,"s","vhsdsa","sbhd",(float)345.0,date,"English","fbxc","hsdsd","dfd",2,3);
	bookDao.modifyBook(book);;
	
	}

}

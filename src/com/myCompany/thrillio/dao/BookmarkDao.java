package com.myCompany.thrillio.dao;

import com.myCompany.thrillio.DataStore;
import com.myCompany.thrillio.entities.Bookmark;
import com.myCompany.thrillio.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks(){
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
}

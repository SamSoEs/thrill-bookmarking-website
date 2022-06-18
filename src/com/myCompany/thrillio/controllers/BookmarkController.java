package com.myCompany.thrillio.controllers;

import com.myCompany.thrillio.entities.Bookmark;
import com.myCompany.thrillio.entities.User;
import com.myCompany.thrillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();
	private BookmarkController() {};
	
	public static BookmarkController getInstance() {
		return instance;
	}
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}
}

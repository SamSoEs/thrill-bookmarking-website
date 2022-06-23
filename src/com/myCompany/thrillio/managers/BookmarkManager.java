package com.myCompany.thrillio.managers;

import com.myCompany.thrillio.dao.BookmarkDao;
import com.myCompany.thrillio.entities.Book;
import com.myCompany.thrillio.entities.Bookmark;
import com.myCompany.thrillio.entities.Movie;
import com.myCompany.thrillio.entities.User;
import com.myCompany.thrillio.entities.UserBookmark;
import com.myCompany.thrillio.entities.WebLink;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();
	private BookmarkManager() {
	};

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();

		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);
		return movie;
	}
	
	
	public Book createBook(long id, String title, int publicationYear, String publisher,
			String[] authors, String genre, double amazonRating) {
		Book book = new Book();

		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);
		return book;
	}
	
	public WebLink createWebLink (long id, String title, String url, String host) {
		WebLink webLink = new WebLink();

		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setUrl(url);
		webLink.setHost(host);
		
		return webLink;
	}
	public Bookmark[][] getBookmarks(){
		return dao.getBookmarks();
	}
	
	public void saveUserBookmark(User user, Bookmark bookmark ) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setBookmark(bookmark);
		userBookmark.setUser(user);
		
		dao.saveUserBookmark(userBookmark);
	}

	public static void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", " + bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		if(bookmark instanceof Book) {
			System.out.println(((Book)bookmark).getItemData());
		}
		else if(bookmark instanceof WebLink) {
			System.out.println(((WebLink)bookmark).getItemData());
		}
		
	}
}

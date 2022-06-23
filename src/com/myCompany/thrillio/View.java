package com.myCompany.thrillio;

import com.myCompany.thrillio.constants.KidFriendlyStatus;
import com.myCompany.thrillio.constants.UserType;
import com.myCompany.thrillio.controllers.BookmarkController;
import com.myCompany.thrillio.entities.Bookmark;
import com.myCompany.thrillio.entities.User;
import com.myCompany.thrillio.partner.Shareable;

public class View {
	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items ...");
		int bookmarkCount = 0;
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking!!
				if (bookmark != null) {
					if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
						boolean isBookmarked = getBookmarkDecision(bookmark);
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New Item bookmarked..." + bookmark);

					}

					if (user.getUserType().equals(UserType.EDITOR)
							|| user.getUserType().equals(UserType.CHIEF_EDITOR)) {
						if (bookmark.isKidFriendlyEligible()
								&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
							String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
							if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
								BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus,
										bookmark);

							}
						}
						if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
								&& bookmark instanceof Shareable) {
							if (getShareDecision()) {
								BookmarkController.getInstance().share(user, bookmark);
							}
						}
					}
				}
			}
		}

	}

	private static boolean getShareDecision() {

		return Math.random() < 0.5 ? true : false;
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		double randomVal = Math.random();

		return randomVal < 0.4 ? KidFriendlyStatus.APPROVED
				: (randomVal >= 0.4 && randomVal < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;

	}

//	public static void bookmark(User user, Bookmark [][] bookmarks) {
//		System.out.println("\n" + user.getEmail() + " is bookmarking");
//		 for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
//				 int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
//				 int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
//				 Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//				 
//				 BookmarkController.getInstance().saveUserBookmark(user, bookmark);
//				 System.out.println(bookmark);
//		 }
//	}

}

package com.myCompany.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.myCompany.thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: porn in in url -- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html", "http://www.javaworld.com");
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For porn in url - isKidFrienldy( must return false)", isKidFriendlyEligible);
		// Test 2: porn in title -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For porn in title - isKidFrienldy( must return false)", isKidFriendlyEligible);
		// Test 3: adult in host -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For adult in host - isKidFrienldy( must return false)", isKidFriendlyEligible);

		// Test 4: adult in url but not in host part -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult in url but not in host part - isKidFrienldy( must return true)", isKidFriendlyEligible);
		// Test 5: adult in title only -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult in title - isKidFrienldy( must return true)", isKidFriendlyEligible);
	}

}

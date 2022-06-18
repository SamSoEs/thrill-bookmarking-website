package com.myCompany.thrillio.dao;

import com.myCompany.thrillio.DataStore;
import com.myCompany.thrillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}

}

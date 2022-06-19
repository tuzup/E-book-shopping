package com.cognizant.ebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URI;
import java.net.URISyntaxException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionHandler {

	public static Connection getConnection() {
		URI dbUri;
		try {
		dbUri = new URI(System.getenv("DATABASE_URL")); 
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		return DriverManager.getConnection(dbUrl, username, password);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
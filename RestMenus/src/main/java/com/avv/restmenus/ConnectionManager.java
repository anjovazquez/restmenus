package com.avv.restmenus;

import java.util.Properties;

import com.ontimize.util.rmi.ConnectionBean;

public class ConnectionManager {

	public final static String HOSTNAME = "Hostname";
	public final static String PORT = "Port";
	public final static String REMOTE_LOCATOR = "RemoteLocatorName";
	public final static String USER = "User";
	public final static String PASSWORD = "Password";

	public final static String ONTIMIZE_CONNECTION_HOST = "localhost";
	public final static String ONTIMIZE_CONNECTION_PORT = "49015";
	public final static String ONTIMIZE_CONNECTION_LOCATOR = "ServerName";
	public final static String ONTIMIZE_CONNECTION_USER = "admin";
	public final static String ONTIMIZE_CONNECTION_PASSWORD = "angel";

	private static Properties params;

	private ConnectionManager() {
		try {
			if (params == null) {
				params = new Properties();
				params.load(RestMenus.class.getResourceAsStream("connection.properties"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ConnectionManager connectionManager = null;

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public ConnectionBean openConnection() {
		if (params == null || params.isEmpty()) {
			params.put(HOSTNAME, ONTIMIZE_CONNECTION_HOST);
			params.put(PORT, ONTIMIZE_CONNECTION_PORT);
			params.put(REMOTE_LOCATOR, ONTIMIZE_CONNECTION_LOCATOR);
			params.put(USER, ONTIMIZE_CONNECTION_USER);
			params.put(PASSWORD, ONTIMIZE_CONNECTION_PASSWORD);
		}
		return new ConnectionBean(params);
	}

	public void closeConnection(ConnectionBean connection) {
		try {
			connection.endSession(connection.getSessionId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

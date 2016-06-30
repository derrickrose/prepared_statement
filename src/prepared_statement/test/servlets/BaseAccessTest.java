package prepared_statement.test.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseAccessTest {

	private String driver = "com.mysql.jdbc.Driver";
	private String baseUrl = "jdbc:mysql://localhost:3306/base";
	private String login = "root";
	private String pass = "";
	private ArrayList<String> message = new ArrayList<String>();
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	private int updateStatus = 0;

	public ArrayList<String> getMessage() {
		return this.message;
	}

	private void init() {
		this.driverLoader();
		this.connectionHandler();
	}

	public ResultSet executeQuery(String query, boolean isPreparedStatement)
			throws SQLException {
		this.init();
		if (isPreparedStatement) {
			this.preparedStatementHandler(query);
			this.resultSet = this.preparedStatement.executeQuery();
		} else {
			this.statementHandler();
			this.resultSet = this.statement.executeQuery(query);
		}
		return this.resultSet;
	}

	public void executeUpdate(String query, boolean isPreparedStatement,
			Object[] paramValues) throws SQLException {
		this.init();
		if (isPreparedStatement) {
			this.message.add("Create prepared update statement : " + query);
			this.preparedStatement = this.connection.prepareStatement(query,
					PreparedStatement.RETURN_GENERATED_KEYS);
			this.message.add("Creating prepared update statement status OK!");
			this.message
					.add("Getting an setting prepared update statement parameterValues!");
			int i = 0;
			for (Object obj : paramValues) {
				i++;
				if (obj instanceof String) {
					this.preparedStatement.setString(i, String.valueOf(obj));
				} else if (obj instanceof Integer) {
					this.preparedStatement.setInt(i, (int) obj);
				}
			}

			this.message
					.add("Execute prepared update statement - with parameterValues!");
			this.updateStatus = this.preparedStatement.executeUpdate();
			this.message.add("Execute prepared update statement status : "
					+ this.updateStatus);
			this.resultSet = this.preparedStatement.getGeneratedKeys();
			while (this.resultSet.next()) {
				this.message.add("Last generated key : "
						+ this.resultSet.getInt(1));
			}
		}
		// return this.resultSet;
	}

	public void executeUpdate(String query) throws SQLException {
		this.init();
		this.statementHandler();
		this.message.add("Execute update : " + query);
		this.updateStatus = this.statement.executeUpdate(query,
				Statement.RETURN_GENERATED_KEYS);
		this.message.add("Executing update status : " + this.updateStatus);
		this.resultSet = statement.getGeneratedKeys();
		while (this.resultSet.next()) {
			this.message
					.add("Last generated key : " + this.resultSet.getInt(1));
		}
		// return this.resultSet;
	}

	private void driverLoader() {
		try {
			this.message.add("Load driver!");
			Class.forName(this.driver);
			this.message.add("Loading driver status OK!");
		} catch (ClassNotFoundException e) {
			this.message.add("Error on loading driver : " + e.getMessage());
		}
	}

	private void connectionHandler() {
		try {
			this.message.add("Handle connection!");
			this.connection = DriverManager.getConnection(this.baseUrl,
					this.login, this.pass);
			this.message.add("Handling connection status OK!");
		} catch (SQLException e) {
			this.message.add("Error on creating connection instance : "
					+ e.getMessage());
		}
	}

	private void statementHandler() {
		try {
			this.message.add("Create statement!");
			this.statement = this.connection.createStatement();
			this.message.add("Create statement status OK!");
		} catch (SQLException e) {
			this.message.add("Error on creating statement : " + e.getMessage());
		}
	}

	private void preparedStatementHandler(String query) {
		try {
			this.message.add("Create prepared statement with query :" + query);
			this.preparedStatement = this.connection.prepareStatement(query);
			this.message.add("Creating prepared statement status OK!");
		} catch (SQLException e) {
			this.message.add("Error on creating prepared statement : "
					+ e.getMessage());
		}
	}

}

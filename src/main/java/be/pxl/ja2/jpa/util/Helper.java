package be.pxl.ja2.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Helper {

	public static void checkData(String tableName) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb", "user", "password");
		//conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		Statement st = conn.createStatement();

		System.out.println("Table Name: " + tableName);

		ResultSet rs = st.executeQuery("select * from " + tableName);
		ResultSetMetaData metadata = rs.getMetaData();
		while (rs.next()) {
			for (int i = 0; i < metadata.getColumnCount(); i++) {
				System.out.print(metadata.getColumnLabel(i + 1) + ": ");
				Object value = rs.getObject(i + 1);
				System.out.print(value + " ,");
			}
			System.out.println();
		}
	}
}

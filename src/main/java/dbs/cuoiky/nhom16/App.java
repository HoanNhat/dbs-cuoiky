package dbs.cuoiky.nhom16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	public static void main(String[] args) {
		// Thông tin kết nối
		String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orclpdb";
		String username = "admin";
		String password = "admin";
		Connection connection = null;

		// Kết nối
		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.println("-----------CONNECT SUCCESSFULLY-----------!");
			Statement statement = connection.createStatement();

			// Truy vấn để lấy tất cả dữ liệu từ bảng 'department'
			String sql = "SELECT * FROM department";

			// Thực thi truy vấn
			ResultSet resultSet = statement.executeQuery(sql);

			// Xử lý kết quả	
			while (resultSet.next()) {
				// Giả sử bảng 'department' có các cột: department_id, department_name
				int departmentId = resultSet.getInt("dept_id");
				String departmentName = resultSet.getString("dept_name");

				// Hiển thị thông tin
				System.out.println("Department ID: " + departmentId + ", Department Name: " + departmentName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("-----------CONNECT FAIL-----------!");
		} finally {
			if (connection != null) {
				try {
					connection.close();
					System.out.println("-----------CONNECT CLOSE-----------!");
				} catch (SQLException e) {
					System.err.println("Error closing connection: " + e.getMessage());
				}
			}
		}
	}
}
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.MySQLConnection;
import model.News;

public class NewsDao {

	private MySQLConnection mysqlconnection;
	private Connection connection;

	public NewsDao() {
		connection = mysqlconnection.getConnection();
	}

	public boolean addNews(News news) {
		String query = "INSERT INTO News (Title, Content) VALUES(?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, news.getTitle());
			stmt.setString(2, news.getContent());

			int rowInserted = stmt.executeUpdate();
			return rowInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNew(News news) {
		String query = "UPDATE News SET Title = ?, Content = ? WHERE Id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(3, news.getId());
			stmt.setString(2, news.getTitle());
			stmt.setString(1, news.getContent());

			int rowsUpdate = stmt.executeUpdate();
			return rowsUpdate > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNews(int Id) {
		String query = "DELETE FROM News WHERE Id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, Id);
			int rowDeleted = stmt.executeUpdate();
			return rowDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public News findById(int Id) {
		News news = null;
		String query = "SELECT * FROM News WHERE Id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, Id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					news = new News(rs.getInt("Id"), rs.getString("Title"), rs.getString("Content"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return news;
	}

	public List<News> getAllNews() {
		List<News> newsList = new ArrayList<>();
		String query = "SELECT * FROM News";

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				News news = new News(rs.getInt("Id"), rs.getString("Title"), rs.getString("Content"));
				newsList.add(news);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newsList;
	}

}

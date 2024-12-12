package Model;

import Controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Books {
    private int books_id;
    private String title;
    private String author;
    private String genre;
    private int price;

    public Books(int books_id, String title, String author, String genre, int price) {
        this.books_id = books_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }
    

    public Books() {
    }


    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Books getBook(int bookId) {
        DatabaseHandler conn = new DatabaseHandler();
        Books book = new Books();

        try {
            conn.connect();
            String query = "SELECT * FROM books WHERE book_id=?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                book.setBooks_id(rs.getInt("books_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPrice(rs.getInt("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return book;
    }

    

    

}

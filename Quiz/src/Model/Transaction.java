package Model;

import Controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    private int transactions_id;
    private Users user;
    private Books books;

    public Transaction(int transactions_id, Users user, Books books) {
        this.transactions_id = transactions_id;
        this.user = user;
        this.books = books;
    }

    public int getTransactions_id() {
        return transactions_id;
    }

    public void setTransactions_id(int transactions_id) {
        this.transactions_id = transactions_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Transaction() {
    }

    public static Transaction getTransaction(int transactionId) {
        DatabaseHandler conn = new DatabaseHandler();
        Transaction transaction = new Transaction();

        try {
            conn.connect();
            String query = "SELECT * FROM transactions WHERE transaction_id=?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transaction.setTransactions_id(rs.getInt("transaction_id"));

                int userId = rs.getInt("user_id");
                Users user = Users.getUser(userId);
                transaction.setUser(user);

                int bookId = rs.getInt("books_id");
                Books book = Books.getBook(bookId);
                transaction.setBooks(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return transaction;
    }
}

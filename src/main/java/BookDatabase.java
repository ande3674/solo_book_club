import java.io.File;
import java.sql.*;
import java.util.Vector;

public class BookDatabase {

    // Need the URL to connect to the DB
    private static final String DB_CONNECTION_URL = "jdbc:sqlite:books.sqlite";

    // Need the names of the books table columns
    private static final String ISBN_COLUMN = "isbn";
    private static final String TITLE_COLUMN = "title";
    private static final String AUTHOR_COLUMN = "author";
    private static final String YEAR_COLUMN = "year";
    private static final String PLOT_COLUMN = "plot";
    private static final String READ_COLUMN = "read";
    private static final String REVIEW_COLUMN = "review";

    // SQL Statements
    private static final String SELECT_UNREAD_LIST = "SELECT * FROM books WHERE " + READ_COLUMN + " IS 0";
    private static final String SELECT_BOOK_BY_ISBN = "SELECT * FROM books WHERE " + ISBN_COLUMN + " IS ";
    private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM books WHERE " + TITLE_COLUMN + " IS '";

    // Constructor
    BookDatabase() {}

    // Get all books that are unread
    Vector<Vector> getUnreadBooks() {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_UNREAD_LIST);

            // vectors will store our results
            Vector<Vector> vectors = new Vector<>();

            String isbn, title, author;
            int year;

            while (rs.next()){
                isbn = rs.getString(ISBN_COLUMN);
                title = rs.getString(TITLE_COLUMN);
                author = rs.getString(AUTHOR_COLUMN);
                year = rs.getInt(YEAR_COLUMN);

                //System.out.println("ISBN: " + isbn +  ", TITLE: " + title);

                Vector v = new Vector();
                v.add(isbn); v.add(title); v.add(author); v.add(year);

                vectors.add(v);
            }
            rs.close();

            // Now pick one of these books to return to the user as their next book to read
            return vectors;
        }
        catch (SQLException sqle){
            throw new RuntimeException(sqle);
        }
    }

    Vector getBookByISBN(String isbn) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_BOOK_BY_ISBN + isbn);

            Vector v = new Vector();

            String title, author;
            int year;

            title = rs.getString(TITLE_COLUMN);
            author = rs.getString(AUTHOR_COLUMN);
            year = rs.getInt(YEAR_COLUMN);

            v.add(isbn); v.add(title); v.add(author); v.add(year);

            rs.close();

            //System.out.println("ISBN: " + isbn +  ", TITLE: " + title);

            return v;

        }
        catch (SQLException sqle){
            throw new RuntimeException(sqle);
        }
    }

    Vector getBookByTitle(String title) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_BOOK_BY_TITLE + title + "'");

            Vector v = new Vector();

            String isbn, author;
            int year;

            isbn = rs.getString(ISBN_COLUMN);
            author = rs.getString(AUTHOR_COLUMN);
            year = rs.getInt(YEAR_COLUMN);

            v.add(isbn); v.add(title); v.add(author); v.add(year);

            rs.close();

            System.out.println("ISBN: " + isbn +  ", TITLE: " + title);

            return v;

        }
        catch (SQLException sqle){
            throw new RuntimeException(sqle);
        }
    }
}

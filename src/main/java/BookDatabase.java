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
    private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM books WHERE " + TITLE_COLUMN + " LIKE '%";

    private static final String PREP_ADD_BOOK_TO_DB = "INSERT INTO books VALUES ( ?, ?, ?, ?, ?, ?, ? )";

    private static final String UPDATE_REVIEW_BEG = "UPDATE books SET " + REVIEW_COLUMN + "= '";// WHERE ISBN = ";
    private static final String UPDATE_REVIEW_END = "' WHERE " + ISBN_COLUMN + "= ";
    private static final String UPDATE_READ_STATUS = "UPDATE books SET " + READ_COLUMN + "=1 WHERE " + ISBN_COLUMN + "=";

    // Constructor
    BookDatabase() {}

    int updateReview(String review, String isbn){
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {
            String fullSQLStatement1 = UPDATE_REVIEW_BEG + review + UPDATE_REVIEW_END + isbn;
            String fullSQLStatement2 = UPDATE_READ_STATUS + isbn;

            statement.executeUpdate(fullSQLStatement1);
            statement.executeUpdate(fullSQLStatement2);

            statement.close();

            return 1;

        }
        catch (SQLException sqle){
            return 0;
        }
    }

    // Add a book to DB
    int addBookToDB(String isbn, String title, String author, int year, String plot, boolean read, String review){
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement pInsert = conn.prepareStatement(PREP_ADD_BOOK_TO_DB)) {
            pInsert.setString(1, isbn);
            pInsert.setString(2, title);
            pInsert.setString(3, author);
            pInsert.setInt(4, year);
            pInsert.setString(5, plot);
            pInsert.setBoolean(6, read);
            pInsert.setString(7, review);

            pInsert.executeUpdate();

            pInsert.close();

            return 1;

        }
        catch (SQLException sqle){
            return 0;
            //throw new RuntimeException(sqle);
        }
    }

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

            String title, author, review;
            int year;

            title = rs.getString(TITLE_COLUMN);
            author = rs.getString(AUTHOR_COLUMN);
            year = rs.getInt(YEAR_COLUMN);
            review = rs.getString(REVIEW_COLUMN);

            v.add(isbn); v.add(title); v.add(author); v.add(year); v.add(review);
            //System.out.println("ISBN: " + isbn +  ", TITLE: " + title);
            rs.close();

            return v;

        }
        catch (SQLException sqle){
            return null;
            //throw new RuntimeException(sqle);
        }
    }

    Vector getBookByTitle(String title) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            ResultSet rs = statement.executeQuery(SELECT_BOOK_BY_TITLE + title + "%'");

            Vector v = new Vector();

            String isbn, author, review;
            int year;

            isbn = rs.getString(ISBN_COLUMN);
            String actualTitle = rs.getString(TITLE_COLUMN);
            author = rs.getString(AUTHOR_COLUMN);
            year = rs.getInt(YEAR_COLUMN);
            review = rs.getString(REVIEW_COLUMN);

            v.add(isbn); v.add(actualTitle); v.add(author); v.add(year); v.add(review);

            rs.close();

            return v;
        }
        catch (SQLException sqle){
            //throw new RuntimeException(sqle);
            return null;
        }
    }
}

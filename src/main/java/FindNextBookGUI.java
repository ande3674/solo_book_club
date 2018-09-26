import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class FindNextBookGUI extends JDialog {
    private JLabel nextBookLabel;
    private JTextField nextBookTextField;
    private JButton nextButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JTextArea apiInfoTextArea;

    private BookDatabase db;

    protected FindNextBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Find Your Next Read");
        setPreferredSize(new Dimension(600, 600));

        mainPanel.getRootPane().setDefaultButton(nextButton);

        apiInfoTextArea.setLineWrap(true);

        pack();

        // Put book information into the text bar upon opening of this window
        // Search the database for the list of unread books using the db object's method
        // If this list contains at least one book, select one book from this list to display
        // else user needs to add some more books to their unread book list
        displayUnreadBook();

        addListeners();
    }

    private void addListeners() {

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUnreadBook();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(FindNextBookGUI.this, "Exit this screen?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });

    }

    private void displayUnreadBook() {
        // Put book information into the text bar upon opening of this window
        // Search the database for the list of unread books using the db object's method
        // If this list contains at least one book, select one book from this list to display
        // else display message for user to update their unread library book list
        Vector<Vector> unreadBooks = db.getUnreadBooks();

        // isbn, title, author, year - the format of the returned vector
        if (unreadBooks.size() > 0){ // make sure this isn't an empty list
            Random rand = new Random();
            int r = rand.nextInt(unreadBooks.size()); // make sure the book is chosen at random

            String isbn = (String)unreadBooks.get(r).get(0);
            String title = (String)unreadBooks.get(r).get(1);
            String author = (String)unreadBooks.get(r).get(2);
            //String year = (String)unreadBooks.get(0).get(3);

            String buildString = "*" + title + " by " + author + ", ISBN: " + isbn + "*";

            nextBookTextField.setText(buildString);

            // Use the BookClient methods to pull the book's description from Google Books API...
            // Allows user to determine if they really want to read that book next.
            // If not, they can click the button again to get a different book recommendation
            String url = new BookClient().buildURL(title);
            String info = new BookClient().getDesc2(url);
            apiInfoTextArea.setText(info);
        }
        else{
            JOptionPane.showMessageDialog(FindNextBookGUI.this, "You have no unread books " +
                    "left in your library. Add some books you would like to read and try again!",
                    "Add More Unread Books To Library", JOptionPane.OK_OPTION);
        }
    }
}

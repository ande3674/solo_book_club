import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

public class FindNextBookGUI extends JDialog { // should this be a JDialog and not a JFrame ?!?
    private JLabel nextBookLabel;
    private JTextField nextBookTextField;
    private JButton nextButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JButton lookUpOnGoogleButton;
    private JTextArea apiInfoTextArea;

    private BookDatabase db;

    protected FindNextBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Find Your Next Read");

        mainPanel.getRootPane().setDefaultButton(nextButton);

        pack();

        // Put book information into the text bar upon opening of this window
        // Search the database for the list of unread books using the db object's method
        // If this list contains at least one book, select one book from this list to display
        // else search google books api for a random book (can refine search later)
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
        // else search google books api for a random book (can refine search later)
        Vector<Vector> unreadBooks = db.getUnreadBooks();

        // isbn, title, author, year - the format of the returned vector
        if (unreadBooks.size() > 0){ // make sure this isn't an empty list
            Random rand = new Random();
            int r = rand.nextInt(unreadBooks.size());

            String isbn = (String)unreadBooks.get(r).get(0);
            String title = (String)unreadBooks.get(r).get(1);
            String author = (String)unreadBooks.get(r).get(2);
            //String year = (String)unreadBooks.get(0).get(3);

            String buildString = "*" + title + " by " + author + ", ISBN: " + isbn + "*";

            nextBookTextField.setText(buildString);

        }
        else{
            // TODO API
        }
    }
}

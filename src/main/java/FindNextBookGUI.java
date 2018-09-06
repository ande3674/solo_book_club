import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindNextBookGUI extends JDialog { // TODO should this be a JDialog and not a JFrame ?!?
    private JLabel nextBookLabel;
    private JTextField nextBookTextField;
    private JButton nextButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private BookDatabase db;

    protected FindNextBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Find Your Next Read");

        pack();

        // Put book information into the text bar upon opening of this window
        // Search the database for the list of unread books using the db object's method
        // If this list contains at least one book, select one book from this list to display
        // else search google books api for a random book (can refine search later)
        displayUnreadBook();

        addListeners();
    }

    private void addListeners() {
        // TODO
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(FindNextBookGUI.this, "Exit?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }

    private void displayUnreadBook() {

        // TODO
        // Put book information into the text bar upon opening of this window
        // Search the database for the list of unread books using the db object's method
        // If this list contains at least one book, select one book from this list to display
        // else search google books api for a random book (can refine search later)

    }
}

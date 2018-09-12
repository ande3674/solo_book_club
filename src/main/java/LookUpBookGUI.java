import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// TODO look up book by title should be able to return multiple books matching the title !
// TODO Once a book has been looked up - be able to select and edit it !

public class LookUpBookGUI extends JDialog {
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JButton lookUpBookButton;
    private JButton cancelButton;
    private JTextArea bookInfoTextArea;
    private JPanel mainPanel;
    private JTextField titleTextField;

    private BookDatabase db;

    protected LookUpBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Look Up A Book");

        mainPanel.getRootPane().setDefaultButton(lookUpBookButton);

        pack();

        addListeners();
    }

    private void addListeners() {

        lookUpBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbnSearch = isbnTextField.getText();
                String titleSearch = titleTextField.getText();

                if (titleSearch.trim() != ""){ // default to title search
                    // search database by title
                    Vector bookInfo = db.getBookByTitle(titleSearch);
                    // info that gets returned:
                    String isbn = "";
                    String actualTitle = "";
                    String author = "";
                    int year = 0;

                    if (bookInfo != null){
                        //isbn, title, author, year
                        isbn = (String)bookInfo.get(0);
                        actualTitle = (String)bookInfo.get(1);
                        author = (String)bookInfo.get(2);
                        year = (int)bookInfo.get(3);

                        String buildString = "FOUND MATCH\n" + "ISBN: " + isbn + "\nTitle: " + actualTitle
                                + "\nAuthor: " + author + "\nYear: " + Integer.toString(year);
                        bookInfoTextArea.setText(buildString);
                    }
                    else {
                        bookInfoTextArea.setText("Book not found in database. Try again.");
                    }
                }
                else {
                    // search the database for the ISBN
                    Vector bookInfo = db.getBookByISBN(isbnSearch);
                    // the info that gets returned:
                    String title = "";
                    String author = "";
                    int year = 0;
                    //bookInfo = isbn, title, author, year
                    if (bookInfo != null) {
                        title = (String) bookInfo.get(1);
                        author = (String) bookInfo.get(2);
                        year = (int) bookInfo.get(3);

                        String buildString = "FOUND MATCH\n" + "ISBN: " + isbnSearch + "\nTitle: " + title
                                + "\nAuthor: " + author + "\nYear: " + Integer.toString(year);
                        bookInfoTextArea.setText(buildString);
                    } else {
                        bookInfoTextArea.setText("Book not found in database. Try again.");
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(LookUpBookGUI.this, "Exit this screen?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}

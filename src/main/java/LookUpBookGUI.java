import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class LookUpBookGUI extends JDialog {
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JButton lookUpBookButton;
    private JButton cancelButton;
    private JTextArea bookInfoTextArea;
    private JPanel mainPanel;
    private JTextField titleTextField;
    private JButton addReviewButton;

    private BookDatabase db;

    private String actualTitle;

    protected LookUpBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Look Up A Book");
        setPreferredSize(new Dimension(600, 400));

        mainPanel.getRootPane().setDefaultButton(lookUpBookButton);

        pack();

        addListeners();
    }

    private void addListeners() {

        lookUpBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookInfoTextArea.setText("");

                String isbnSearch = isbnTextField.getText();
                String titleSearch = titleTextField.getText();
                // else if just one is populated - look that one up only
                // else show error message

                // if both fields are populated - look up isbn first and then title
                if (isbnSearch.trim().equals("") && titleSearch.trim().equals("")){
                    JOptionPane.showMessageDialog(LookUpBookGUI.this,
                            "Error: you must enter either an ISBN or a title to search.", "Error", JOptionPane.OK_OPTION );

                }
                else if (!isbnSearch.trim().equals("")) { // && titleSearch != null && titleSearch.trim() != ""){
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

                else if (!titleSearch.trim().equals("")){ // title search
                    // search database by title
                    Vector bookInfo = db.getBookByTitle(titleSearch);
                    // info that gets returned:
                    String isbn = "";
                    //String actualTitle = "";
                    String author = "";
                    int year = 0;

                    if (bookInfo != null){
                        //isbn, title, author, year
                        isbn = (String)bookInfo.get(0);
                        actualTitle = (String)bookInfo.get(1);
                        author = (String)bookInfo.get(2);
                        year = (int)bookInfo.get(3);

                        String buildString = "FOUND MATCH \n" + "ISBN: " + isbn + " \nTitle: " + actualTitle
                                + "\nAuthor: " + author + "\nYear: " + Integer.toString(year);
                        bookInfoTextArea.setText(buildString);
                    }
                    else {
                        bookInfoTextArea.setText("Book not found in database. Try again.");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(LookUpBookGUI.this,
                            "Error: you must enter either an ISBN or a title to search.", "Error", JOptionPane.OK_OPTION );
                }
            }
        });

        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!bookInfoTextArea.getText().trim().equals("")) {
                    // What is the title and isbn?
                    //String buildString = "FOUND MATCH\n" + "ISBN: " + isbnSearch + "\nTitle: " + title
                    //                                + "\nAuthor: " + author + "\nYear: " + Integer.toString(year);
                    String bookText = bookInfoTextArea.getText();
                    String[] bookTextSplit = bookText.split(" ");
                    String isbn = bookTextSplit[3];

                    // Open a new UpdateReviewGUI frame
                    UpdateReviewGUI urg = new UpdateReviewGUI(db, actualTitle, isbn);
                }

                else {
                    JOptionPane.showMessageDialog(LookUpBookGUI.this, "Must look up a book before you can add a review.", "Error", JOptionPane.OK_OPTION);
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

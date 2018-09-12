import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// TODO add feature to look up book by title !

public class LookUpBookGUI extends JDialog {
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JButton lookUpBookButton;
    private JButton cancelButton;
    private JTextArea bookInfoTextArea;
    private JPanel mainPanel;

    private BookDatabase db;

    protected LookUpBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Look Up A Book");

        pack();

        addListeners();
    }

    private void addListeners() {

        lookUpBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnTextField.getText();

                // search the database for the ISBN
                Vector bookInfo = db.getBookByISBN(isbn);
                // the info that gets returned:
                String title = "";
                String author = "";
                int year = 0;

                //bookInfo = isbn, title, author, year
                if (bookInfo.size() > 0){
                    title = (String)bookInfo.get(1);
                    author =(String)bookInfo.get(2);
                    year = (int)bookInfo.get(3);

                    String buildString = "FOUND MATCH\n" + "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nYear: " + Integer.toString(year);
                    bookInfoTextArea.setText(buildString);
                }

                else {
                    bookInfoTextArea.setText("Book not found in database. Try again.");
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(LookUpBookGUI.this, "Exit?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}

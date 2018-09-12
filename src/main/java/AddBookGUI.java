import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookGUI extends JDialog {
    private JLabel titleLabel;
    private JTextField titleTextField;
    private JLabel authorLabel;
    private JLabel isbnLabel;
    private JLabel yearLabel;
    private JLabel readLabel;
    private JTextField authorTextField;
    private JTextField isbnTextField;
    private JTextField yearTextField;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JLabel reviewLabel;
    private JTextArea reviewTextArea;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private BookDatabase db;

    protected AddBookGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Add A Book To Your Reading List");

        pack();

        addListeners();
    }

    private void addListeners() {

        // TODO this is close but is definitely not working exactly right and needs to be updated!!!!!!!!
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //make sure all of the info is there
                String isbn = isbnTextField.getText();
                String title = titleTextField.getText();
                String author = authorTextField.getText();
                String yearString = yearTextField.getText();
                int year = Integer.parseInt(yearString);
                String plot = "";
                String review = reviewTextArea.getText();

                boolean read;
                if (yesRadioButton.isSelected()) {
                    read = true; }
                else {
                    read = false;
                }

                if ((isbn.trim() == "" || title.trim() == "" || author.trim() == "")){

                    JOptionPane.showMessageDialog(AddBookGUI.this, "Error: Please enter a " +
                                        "review if you have read the book, otherwise change read selection.");

                }
                else {
                    db.addBookToDB(isbn, title, author, year, plot, read, review);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(AddBookGUI.this, "Exit?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}

import javax.swing.*;

public class AddBookGUI extends JFrame {
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

        //addListeners();
    }
}

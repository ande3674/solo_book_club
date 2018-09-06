import javax.swing.*;

public class LookUpBookGUI extends JFrame {
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

        //addListeners();
    }
}

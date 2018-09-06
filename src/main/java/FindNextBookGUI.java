import javax.swing.*;

public class FindNextBookGUI extends JFrame {
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

        //addListeners();
    }
}

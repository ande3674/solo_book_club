import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

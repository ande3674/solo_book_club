import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheSoloBookClubMainGUI extends JFrame {
    private JButton findNextReadButton;
    private JButton bookLookUpButton;
    private JLabel titleLabel;
    private JPanel mainPanel;
    private JButton addNewBookButton;

    private BookDatabase db;

    protected TheSoloBookClubMainGUI(BookDatabase db){
        this.db = db;

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Ali's Solo Book Club Application");

        pack();

        addListeners();
    }

    private void addListeners() {

        findNextReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Open the next read window
                FindNextBookGUI fnbg = new FindNextBookGUI(db);
            }
        });
        bookLookUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LookUpBookGUI lubg = new LookUpBookGUI(db);

            }
        });
        addNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddBookGUI abg = new AddBookGUI(db);

            }
        });
    }


}

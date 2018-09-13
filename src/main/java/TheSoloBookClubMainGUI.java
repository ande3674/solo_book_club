import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheSoloBookClubMainGUI extends JFrame {
    private JButton findNextReadButton;
    private JButton bookLookUpButton;
    private JLabel titleLabel;
    private JPanel mainPanel;
    private JButton addNewBookButton;
    private JButton exitProgramButton;
    private JButton talkToAPIButton;
    private JLabel apiLabel;

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
        exitProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(TheSoloBookClubMainGUI.this,
                        "Are you sure you want to exit the program?", "Exit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        talkToAPIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apiInfo = new BookClient().getHarry();
                apiLabel.setText(apiInfo);

            }
        });
    }
}
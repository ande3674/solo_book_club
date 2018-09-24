import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateReviewGUI extends JDialog {
    private JLabel titleLabel;
    private JTextArea reviewTextArea;
    private JButton saveReviewButton;
    private JButton exitButton;
    private JPanel mainPanel;

    private BookDatabase db;
    private String title;
    private String isbn;

    protected UpdateReviewGUI(BookDatabase db, String title, String isbn) {
        this.db = db;
        this.title = title;
        this.isbn = isbn;
        titleLabel.setText(title);

        // Regular setup stuff for the window / JFrame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Update Book Review");
        setPreferredSize(new Dimension(600, 400));

        mainPanel.getRootPane().setDefaultButton(saveReviewButton);

        pack();

        addListeners();
    }

    private void addListeners() {

        saveReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String review = reviewTextArea.getText();

                if (!review.trim().equals("")) {

                    int status = db.updateReview(review, isbn);

                    if (status == 1) {
                        reviewTextArea.setText("");
                        setVisible(false);
                        JOptionPane.showMessageDialog(UpdateReviewGUI.this, "Book review updated " +
                                "successfully.");
                    } else if (status == 0) {
                        JOptionPane.showMessageDialog(UpdateReviewGUI.this, "There was an error " +
                                "updating the book review in your library.");
                    }
                }

                else {
                    JOptionPane.showMessageDialog(UpdateReviewGUI.this, "Error: Enter a review.", "Error", JOptionPane.OK_OPTION);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of this window but keep program running
                if (JOptionPane.showConfirmDialog(UpdateReviewGUI.this, "Exit this screen?",
                        "Exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                    setVisible(false);
                }
            }
        });
    }
}

public class Main {

    public static void main(String[] args) {

        BookDatabase db = new BookDatabase();
        TheSoloBookClubMainGUI gui = new TheSoloBookClubMainGUI(db);

        // Test stuff...
        //db.updateReview("Pretty good, 4/5 Stars", "9788581630359");

    }
}

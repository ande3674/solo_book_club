public class Main {

    public static void main(String[] args) {

        BookDatabase db = new BookDatabase();
        //db.getUnreadBooks();
        //db.getBookByISBN("9788581630359");
        //db.getBookByTitle("Middlesex");

        TheSoloBookClubMainGUI gui = new TheSoloBookClubMainGUI(db);

    }
}

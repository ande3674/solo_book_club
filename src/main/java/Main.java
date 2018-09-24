public class Main {

    public static void main(String[] args) {

        BookDatabase db = new BookDatabase();
        TheSoloBookClubMainGUI gui = new TheSoloBookClubMainGUI(db);

        /*
        // Test stuff...
        //db.updateReview("Pretty good, 4/5 Stars", "9788581630359");
        String buildString = "FOUND MATCH \n" + "ISBN: " + "9780011111" + " \nTitle: " + "This is The title"
                + "\nAuthor: " + "Some b" + "\nYear: " + "1978";
        String[] bookTextSplit = buildString.split(" ");
        for (int i = 0 ; i < bookTextSplit.length ; i++) {
            System.out.println(Integer.toString(i) + " : " + bookTextSplit[i]);
        }
        String isbn = bookTextSplit[3];
        System.out.println(isbn);
        */

    }
}

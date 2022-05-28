package projects.project08;

import java.util.Scanner;

/**
 * Αναπτύξτε ένα παιχνίδι Τρίλιζα, όπου δύο παίκτες παίζουν Χ και Ο (ή 1 και 2 αν θέλετε
 * να υλοποιήσετε με πίνακα ακεραίων και όχι με πίνακα char) και κερδίζει ο παίκτης
 * που έχει συμπληρώσει τρία ίδια σύμβολα ή αριθμούς σε οποιαδήποτε διάσταση του
 * πίνακα, οριζόντια, κάθετα ή διαγώνια.
 * Η main() μπορεί να ελέγχει τη ροή του παιχνιδιού, όπως ποιος παίκτης παίζει κάθε
 * φορά (εναλλαγή μεταξύ των δύο παικτών), να διαβάζει από το stdin το σύμβολο που
 * δίνει ο κάθε παίκτης και να εμφανίζει με γραφικό τρόπο (όπως είχαμε δει σε
 * αντίστοιχο παράδειγμα στην τάξη) την τρίλιζα μετά από κάθε κίνηση κάθε παίκτη.
 * Ενώ, μπορείτε να δημιουργήσετε και μία μέθοδο που να ελέγχει (μετά από κάθε
 * κίνηση) αν ο παίκτης που έκανε την κίνηση έκανε τρίλιζα.
 * Το πρόγραμμα θα πρέπει να λαμβάνει υπόψη την περίπτωση ισοπαλίας όπως και να
 * μην επιτρέπει ένας παίκτης να παίξει σε θέση που είναι ήδη κατειλημμένη
 *
 * # Χρησιμοποιούμε String και όχι char στο array για να διαβάζουμε το σύνολο των χαρακτήρων που έχει επιλέξει ως θέση ο Player.
 * Επίσης, από τη στιγμή που δεν περιμένουμε int ή συγκεκριμένο σύμβολο δεν έχουμε πλέον InputMissMatchException.
 */

public class Project08 {
    static Scanner in = new Scanner(System.in);
    static boolean playerOne = true;
    static boolean oneWins = false;
    static boolean twoWins = false;

    public static void main(String[] args) {
        String[][] trillArr = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8","9"}};
        String usersChoice;
        int[] position= {-1, -1};
        String user1 = "X";
        String user2 = "@";
        boolean isOngoing = true;


        do{
            showTrill(trillArr);
            System.out.printf("\n%s Choose position: ", playerOne? "Player One 'X'" : "Player Two '@'");
            usersChoice= in.next();
            String userSign = playerOne ? user1 : user2;
            handleChoice(trillArr, usersChoice, userSign);
            checkWin(trillArr, user1, user2);
            isOngoing = isFull(trillArr);
            System.out.println();
        } while (!isOngoing & !oneWins & !twoWins);
            showTrill(trillArr);
        System.out.printf("%s WINS!",  oneWins? "Player One 'X'" : twoWins? "Player Two '@'": "No one");
    }

    /**
     * Βρίσκει τη θέση (i, j) που έχει επιλέξει ο παίκτης ώστε να το αλλάξουμε στη συνέχεια
     * με Χ ή Ο. Αν είναι λάθος επιλογή επιστρέφει (-1, -1)
     * @param arr
     * @param value
     * @return
     */
    public static int[] getPosition(String[][] arr, String value){
        int[] position = {-1, -1};
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++)
                if(value != null && arr[i][j] != null ){
                    if (arr[i][j].equals(value) ){
                        position[0] = i;
                        position[1] = j;
                }
            }
        }
        return position;
    }

    public static void showTrill(String[][] arr){
        System.out.println("|---|---|---|");
        for (String[] row:arr){
            for (String column: row) {
                System.out.print("| " + column + " ");
            }
            System.out.println("|");
            System.out.println("|---|---|---|");
        }
    }

    public static void handleChoice(String[][] arr, String choice, String usersSign){
        int[] position = {-1, -1};
        position = getPosition(arr, choice);
        if(position[0] == -1 || position[1] == -1){
            System.out.println("Invalid. Choose between 1-9 or a position not already taken!");
            System.out.printf("%s choose again\n", playerOne? "PlayerOne 'X'" : "PlayerTwo '@'");
        } else if(arr[position[0]][position[1]].equals("X") || arr[position[0]][position[1]].equals("@")){
            System.out.println("Already chosen.");
            System.out.printf("%s choose again\n", playerOne? "PlayerOne 'X'" : "PlayerTwo '@'");
        }else {
            arr[position[0]][position[1]]=usersSign;
            playerOne = !playerOne;
        }
    }

    public static boolean isFull(String[][] arr){
        int count= 0;
        for(String[] row: arr){
            for(String position:row){
                if(position.equals("X") || position.equals("@")){
                    count++;
                }
            }
        }
        return count==9;
    }

    public static void checkWin(String[][] arr, String one, String two){
        //diagonally
        if(arr[0][0].equals(one) && arr[1][1].equals(one) && arr[2][2].equals(one)) {
            oneWins =true;
            return;
        }
        if(arr[0][0].equals(two) && arr[1][1].equals(two) && arr[2][2].equals(two)) {
            twoWins =true;
            return;
        }
        if(arr[0][2].equals(one) && arr[1][1].equals(one) && arr[2][0].equals(one)) {
            oneWins =true;
            return;
        }
        if(arr[0][2].equals(two) && arr[1][1].equals(two) && arr[2][0].equals(two)) {
            twoWins =true;
            return;
        }

        //horizontal
        for (String[] row: arr){
            if(row[0].equals(one) && row[1].equals(one) && row[2].equals(one)) {
                oneWins =true;
                return;
            }
            if(row[0].equals(two) && row[1].equals(two) && row[2].equals(two)) {
                twoWins =true;
                return;
            }
        }

        //vertical
        for(int i = 0; i < arr.length; i++){
           if(arr[0][i].equals(one) && arr[1][i].equals(one) && arr[2][i].equals(one)){
               oneWins =true;
               return;
           }
            if(arr[0][i].equals(two) && arr[1][i].equals(two) && arr[2][i].equals(two)){
                twoWins =true;
                return;
            }

        }
    }
}

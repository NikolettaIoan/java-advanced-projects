package projects.project09;

import java.util.ArrayList;

/**
 * Μία από τις πρώτες εφαρμογές των Η/Υ ήταν η κρυπτογράφηση. Ένας απλός τρόπος
 * κρυπτογράφησης είναι η κωδικοποίηση κάθε χαρακτήρα με ένα ακέραιο με βάση ένα
 * κλειδί κρυπτογράφησης. Μία τέτοια μέθοδος κρυπτογράφησης περιγράφεται στη
 * συνέχεια.
 * Κωδικοποίησε τον 1ο χαρακτήρα του μηνύματος με την ακέραια τιμή που
 * αντιστοιχεί σε αυτόν (από τον κώδικα ASCII). Κωδικοποίησε του επόμενους
 * χαρακτήρες: (α) προσθέτοντας την ακέραια ASCII τιμή του καθένα από αυτούς με τον
 * κωδικό του προηγούμενού του, (β) παίρνοντας το υπόλοιπο της διαίρεσης του
 * αθροίσματος αυτού διά μία σταθερά. Η σταθερά αυτή ονομάζεται κλειδί (key)
 * κρυπτογράφησης και (υποτίθεται πως) είναι μυστική.
 * Υποθέτουμε πως τα μηνύματα τελειώνουν με τον χαρακτήρα #.
 * Γράψτε ένα πρόγραμμα java που να υλοποιεί τον αλγόριθμο κρυπτογράφησης έτσι
 * ώστε το κωδικοποιημένο μήνυμα που προκύπτει να είναι μία ακολουθία ακεραίων
 * που τελειώνει με -1.
 * Γράψτε και τον αλγόριθμο αποκρυπτογράφησης που λαμβάνει ως είσοδο μία
 * ακολουθία ακεραίων που τελειώνει με -1 και υπολογίζει το αρχικό μήνυμα.
 */

public class Project09 {
    public static void main(String[] args) {
        String message = "Hello World#Something else#";
        int key = 209;
        StringBuilder decodedMessage;

        ArrayList<Integer> charMessage = new ArrayList<>();
        charMessage = cifer(message, key);

        for(int value: charMessage){
            System.out.println(value);
        }


        decodedMessage = decifer(charMessage, key);

        System.out.println(decodedMessage);

    }
    public static ArrayList<Integer> cifer(String message,int key){
        ArrayList<Integer> charMessage = new ArrayList<>();
        charMessage.add((int) message.charAt(0));

        for(int i= 1; i < message.length(); i++){
            int codedValue= ((int) message.charAt(i) + charMessage.get(i-1))%key;

            if ((int) message.charAt(i) == (int)'#') {
                charMessage.add(-1);
                break;
            }
            charMessage.add(codedValue);
        }
        return charMessage;
    }

    public static StringBuilder decifer(ArrayList<Integer> arr, int key){
        StringBuilder decoded = new StringBuilder();
        int value = 0;
        int originalValue=0;
        originalValue = arr.get(0);
        decoded.append((char) originalValue);
        for(int i = 1; i < arr.size()-1; i++){

            value = arr.get(i);
            while((value - arr.get(i-1) )< 0){
                value += key;
            }
            originalValue = value - arr.get(i-1);
            decoded.append((char) originalValue);
        }
        return decoded;
    }
}

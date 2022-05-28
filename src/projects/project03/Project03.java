package projects.project03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Αναπτύξτε μία εφαρμογή που διαβάζει έναν-έναν τους χαρακτήρες ενός αρχείου και
 * τους εισάγει σε ένα πίνακα 256x2. Κάθε θέση του πίνακα είναι επομένως ένας
 * πίνακας δύο θέσεων, όπου στην 1η θέση αποθηκεύεται ο χαρακτήρας που έχει
 * διαβαστεί (αν δεν υπάρχει ήδη στον πίνακα) και στην 2η θέση αποθηκεύεται το
 * πλήθος των φορών που έχει διαβαστεί (βρεθεί) κάθε χαρακτήρας. Χαρακτήρες
 * θεωρούνται και τα κενά και οι αλλαγές γραμμής και γενικά οποιοσδήποτε UTF-8
 * χαρακτήρας.
 * Στο τέλος η main() παρουσιάζει στατιστικά στοιχεία για κάθε χαρακτήρα όπως η
 * συχνότητα εμφάνισής του στο κείμενο ταξινομημένα ανά χαρακτήρα και ανά
 * συχνότητα εμφάνισης.
 */

public class Project03 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new FileReader("src/projects/project03/sample.txt"));
        float[][] chars = new float[256][2];  //Ώστε να έχουμε σωστή εικόνα για τα statistics
        int n = 0;
        int len = 0;
        int bufSize = 8192;
        char[] buf = new char[bufSize];
        int pivot =-1;
        float [][] copied ;

        for (float [] row: chars) {
            Arrays.fill(row, 0);
        }

        while ((n = bf.read(buf,0,bufSize)) != -1) {
            sb.append(buf, 0, n);
            len += n;
        }

        System.out.println("len: " + len);
        for (int i =0; i < sb.length() ; i++){
            int position =getPosition(chars, sb.charAt(i));
            if(position == -1){
                pivot++;
                chars[pivot][0] = sb.charAt(i);
                chars[pivot][1]++;
            } else {
                chars[position][1]++;
            }
        }

        copied = new float[pivot+1][2];
        for (int i = 0; i <= pivot;i++){
            copied[i] = Arrays.copyOf(chars[i], 2);
        }

        getStats(copied, len);

        System.out.println("Sorted by char");
        sortedByChar(copied);
        for(int i =0; i < copied.length; i++){
            char letter = (char) copied[i][0];
            System.out.printf("'%s' (ASCCI %.0f): %.2f%% \n",letter , copied[i][0], copied[i][1]);
        }
        System.out.println();
        System.out.println("Sorted by appearence:");

        sortedByCharAppear(copied);
        for(int i =0; i < copied.length; i++){
            System.out.printf("'%s' (ASCCI %.0f): %.2f%% \n",(char) copied[i][0] , copied[i][0], copied[i][1]);
        }

    }

    public static int getPosition(float[][] arr,int value){
        int position = -1;
        for(int i = 0; i < arr.length; i++){
            if (arr[i][0] == value){
                position =i;
            }
        }
        return position;
    }
    public static void getStats(float[][]arr, int len){
        for(float[] row : arr){
            row[1]= (row[1] *100)/ len;
        }
    }

    public static void sortedByChar(float[][] arr){
        Arrays.sort(arr, (a1,a2) -> (int) (a1[0] - a2[0]));
    }

    /**
     * Επειδή κάποια στατιστικά είναι μικρότερα από 0 τα πολλαπλασιάζουμε με 100
     * @param arr
     */
    public static void sortedByCharAppear(float[][] arr){
        Arrays.sort(arr, (a1,a2) -> (int) (a1[1]*100 - a2[1]*100));
    }
}

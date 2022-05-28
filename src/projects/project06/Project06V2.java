package projects.project06;

import java.util.Arrays;

/**
 * Έστω ένας πίνακας n ακεραίων. Τότε ο maximum sub subarray ο είναι ο συνεχόμενος
 * υποπίνακας (contiguous subarray - δυνητικά κενό) με το μεγαλύτερο άθροισμα.
 * Σχεδιάστε έναν γραμμικό αλγόριθμο (με πολυπλοκότητα O(n)) για να επιλύσετε τα
 * παραπάνω πρόβλημα. Για παράδειγμα, αν έχουμε τον πίνακα {−2, 1, −3, 4, −1, 2, 1,
 * −5, 4} τότε ο συνεχόμενος υποπίνακας με το μέγιστο άθροισμα είναι ο {4, −1, 2, 1},
 * του οποίου το άθροισμα είναι 6.
 * Δώστε μια λύση τριών μερών της ακόλουθης μορφής:
 * (α) Περιγράψτε (με λόγια και σχήματα) ξεκάθαρα τον αλγόριθμό σας.
 * (β) Γράψτε τον κώδικα σε Java .
 * (γ) Δείξτε ότι η πολυπλοκότητα χρόνου είναι O(n)
 *
 * Η τελική μορφή της μεθόδου.
 *
 * Η Project06V2 υπολογίσει αρχικά με την localMaxValue το μέγιστο άθροισμα και μεταβάλλει το highIndex, δηλαδή την τελική θέση του subArray
 * μέσα στο array που έχει δοθεί. Επίσης, δίνει αρχικές τιμές στο globalMax, localMax και LowIndex που χρησιμοποιεί
 * η μέθοδος subArray η οποία δίνει τις τελικές τιμές.
 *
 */
public class Project06V2 {
    static int localMax=0;
    static int globalMax=0;
    static int lowIndex=0;
    static int highIndex=0;
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1,-5, 4, -15};
        int[] subArr;

        localMaxValue(arr);

        subArray(arr);

        subArr= Arrays.copyOfRange(arr, lowIndex, highIndex+1);

        for (int value:subArr){
            System.out.print(value + " ");
        }
    }

    /**
     * Αφαιρούμε από το άθροισμα που έχει βρεθεί με τη μέθοδο localMaxValue την τιμή arr[i] διαδοχικά
     * μεταβάλλοντας το localMax ώστε να μεγιστοποιήσουμε τις τελικές τιμές μας.
     * @param arr
     */
    public static void subArray(int[] arr){

        for(int i=0; i <=highIndex; i++){
            localMax = localMax - arr[i];

            if(globalMax<localMax){
                globalMax=localMax;
                lowIndex = i+1;
            }
        }

    }

    /**
     * Υπολογίζει globalMax, localMax,lowIndex =0, HighIndex για όλο το μήκος του arr που έχει δοθεί.
     * HighIndex είναι η τελική θέση του subArr για την οποία έχουμε μέγιστο άθροισμα με i=0;
     * @param arr
     */
    public static void localMaxValue(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (localMax < sum) {
                localMax = sum;
                if (globalMax < localMax) {
                    globalMax = localMax;
                    lowIndex = 0;
                    highIndex = i;
                }
            }
        }

    }
}

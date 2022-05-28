package projects.project06;

import java.util.Arrays;

/**
 * Η Project06V2 έχει καλύτερη πολυπλοκότητα χρόνου. Στην παρακάτω μέθοδο υπολογίζεται κάθε φορά το άθροισμα,
 *άρα νομίζω πως έχει μεγαλύτερο χρόνο εκτέλεσης.
 *
 * Για να βρούμε το maximum sum subarray χρειάζεται να προσθέσουμε διαδοχικά τις τιμές του πίνακα.
 * Χρησιμοποιούμε κάποιες static μεταβλητές ώστε να αποθηκεύουμε τις τιμές και recursive μέθοδο.
 * Διατρέχουμε  από την αρχή μέχρι το array.length με το recursive και μεταβάλλουμε αντίστοιχα τις static μεταβλητές.
 *
 * @localMax ένα τοπικό μέγιστο για να ελέγχουμε το sum που αλλάζει ανά επανάληψη
 * @globalMax το μέγιστο άθροισμα από το υποσύνολο
 * @LowIndex η αρχή του υποσυνόλου
 * @highIndex το τελικό index από το array
 *
 */
public class Project06 {
    static int localMax=0;
    static int globalMax=0;
    static int lowIndex=0;
    static int highIndex=0;
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1,-5, 4, -15};
        int[] subArr;

        recursiveSubArray(arr,0);
//        System.out.println("LM: " + localMax + "GB; " + globalMax + "LI: " + lowIndex + "HI" + highIndex);

        subArr= Arrays.copyOfRange(arr, lowIndex, highIndex+1);

        for (int value:subArr){
            System.out.print(value + " ");
        }


    }

    /**
     * Η υπολογίζει επαναληπτικά τα αθροίσματα και μεταβάλλει τις static μεταβλητές με βάσει το μέγιστο εσωτερικό sum
     * @param arr το array
     * @param n starting point κάθε φορά θέλουμε +1
     * @return μια τυπική μεταβλητή ώστε να μπορούμε να κάνουμε χρήση της recursive και να τη σταματήσουμε
     */
    public static int recursiveSubArray(int[] arr,int n){ //n=starting point
        int sum =0;
        if(n == arr.length) return -1;

        for(int i=n; i < arr.length ; i++){
            sum += arr[i];
            if (localMax <sum){
                localMax =sum;
                if(globalMax<localMax){
                    globalMax =localMax;
                    lowIndex = n;
                    highIndex =i;
                    }
                }
            }

        return recursiveSubArray(arr,n+1);
    }

}

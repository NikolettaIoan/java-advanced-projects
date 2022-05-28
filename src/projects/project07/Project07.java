package projects.project07;

import java.util.Arrays;

/**
 * Γράψτε δύο μεθόδους που αφορούν την αντιγραφή δυσδιάστατων πινάκων. Μία
 * μέθοδο int[][] shallowCopy(int[][] arr) που αντιγράφει ένα δυσδιάστατο πίνακα αλλά
 * μόνο τις τιμές του βασικού πίνακα που είναι αναφορές στους πίνακες που είναι
 * στοιχεία του βασικού πίνακα. Και μία μέθοδο int[][] deepCopy(int[][] arr).
 * Γράψτε μία main που να δείχνετε γιατί το shallow copy δεν δουλεύει όπως θα θέλαμε,
 * αφού αλλάζοντας ένα στοιχείο ενός πίνακα από δύο για παράδειγμα copies, αλλάζει
 * το στοιχείο και στον άλλο πίνακα, αφού κατά βάση πρόκειται για ένα και μόνο κοινό
 * στοιχείο (αφού έχει γίνει shallow copy).
 * Δείξτε και την περίπτωση του deep copy. Δείξτε ότι δουλεύει όπως θα θέλαμε.
 * Δηλαδή δεν επηρεάζουν οι αλλαγές στοιχείων το κάθε copy, το οποίο τώρα είναι
 * ανεξάρτητο.
 */
public class Project07 {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] shallowCopiedArr;
        int[][] deepCopiedArr;

        System.out.println("Shallow Copy example");

        shallowCopiedArr = shallowCopy(arr);

        for(int[] row: shallowCopiedArr){
            for(int item: row){
                System.out.print(item + " ");
            }
            System.out.println();
        }

        shallowCopiedArr[1][2] = 20;

        System.out.println("\nDeep Copy Example");

        deepCopiedArr = deepCopy(arr);

        for(int[] row: deepCopiedArr){
            for(int item: row){
                System.out.print(item + " ");
            }
            System.out.println();
        }

        deepCopiedArr[1][2] = 45;

        System.out.println("arr[1][2]: " + arr[1][2] + " shallowCopiedArr[1][2]: " + shallowCopiedArr[1][2]);
        System.out.println("arr[1][2]: " + arr[1][2] + " deepCopiedArr[1][2]: " + deepCopiedArr[1][2]);
    }

    /**
     * Αντιγραφή χρησιμοποιώντας τη μέθοδο Array.copyOf
     * @param arr το array προς αντιγραφή
     * @return το copied array
     */
    public static int[][] shallowCopy(int[][] arr){
        return Arrays.copyOf(arr, arr.length);
    }

    /**
     * Δημιουργεί ένα νέο array με ίδιο αριθμό σειρών και στήλων και το κάνουμε στη συνέχεια populate με τις
     * τιμές του αρχικού array. Κάνουμε την παραδοχή πως οι σειρές έχουν όλες τον ίδιο αριθμό στηλών, γιατί χρειάζεται αρχικοποίηση.
     * @param arr το array προς αντιγραφή
     * @return το copied array
     */
    public static int[][] deepCopy(int[][] arr){
        int[][] copiedArr =new int[arr.length][arr[0].length]; //Θεωρούμε ότι όλοι οι εσωτερικοί πίνακες έχουν το ίδιο length

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                copiedArr[i][j] = arr[i][j];
            }
        }
        return copiedArr;
    }
}

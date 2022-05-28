package projects.project05;

/**
 * Έστω ένας ταξινομημένος πίνακας με επαναλαμβανόμενα στοιχεία. Γράψτε μία
 * μέθοδο int[] getLowAndHighIndexOf(int[] arr, int key) που να υπολογίζει και να
 * επιστρέφει τα low και high index ενός πίνακα arr, για ένα ακέραιο key που λαμβάνει
 * ως παράμετρο.
 * Γράψτε και μία main() που να βρίσκει το low και high index για τον πίνακα {0, 1, 4, 4,
 * 4, 6, 7, 8, 8, 8, 8, 9}. Για παράδειγμα, αν δώσουμε ως τιμή το 8, θα πρέπει να
 * επιστρέφει {7, 10}.
 */
public class Project05 {
    public static void main(String[] args) {
        int[] arr ={0, 1, 4, 4, 4, 6, 7, 8, 8, 8, 8, 9};
        int[] lowHigh = new int[2];
        int key =8;
        lowHigh= getLowAndHighIndexOf(arr, key);

        if (lowHigh[0]==-1){
            System.out.println("Array does not include key value");
        }else{
            System.out.printf("The key %s in the array has low index %s and %s.", key, lowHigh[0], lowHigh[1]!= -1? String.format("high index %s", lowHigh[1]) : "does not appear a second time");
        }
    }

    public static int[] getLowAndHighIndexOf(int[] arr, int key){
        int[] lowHigh = {-1, -1};

        for(int i =0; i < arr.length; i++){
            if(arr[i] == key && lowHigh[0] == -1){
                lowHigh[0] = i;
            }
            if ((arr[i] == key) && (lowHigh[0] != -1) && (i != lowHigh[0])){
                lowHigh[1] = i;
            }
        }

        return lowHigh;
    }
}

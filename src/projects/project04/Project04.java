package projects.project04;

import java.util.Arrays;

/**
 * Έστω ένας δισδιάστατος πίνακας που περιέχει τα στοιχεία άφιξης και αναχώρησης
 * αυτοκινήτων σε μορφή arr[][] = {{1012, 1136}, {1317, 1417}, {1015, 1020}}
 * Αναπτύξτε μία εφαρμογή που διαβάζει να εκτυπώνει τον μέγιστο αριθμό
 * αυτοκινήτων που είναι σταθμευμένα το ίδιο χρονικό διάστημα. Για παράδειγμα στον
 * παραπάνω πίνακα το αποτέλεσμα θα πρέπει να είναι: 2. Το 1ο αυτοκίνητο αφίχθη
 * στις 10:12 και αναχώρησε στις 11:36, το 3ο αυτοκίνητο αφίχθη στις 10:15 και
 * αναχώρησε στις 10:20. Επομένως, το 1ο και το 3ο αυτοκίνητο ήταν παρόντα το ίδιο
 * χρονικό διάστημα.
 */
public class Project04  {
    public static void main(String[] args) {
        int[][] arr = {{1012, 1136}, {1317, 1417}, {1015, 1020}, {1117, 1330}, {1220, 1400}};
        int[][] inOutTime =new int [arr.length*2][2];
        int globalSum =0;
        int tmpSum=0;

        for (int i =0; i < inOutTime.length; i++){
            if((i % 2) == 0){
                inOutTime[i][0] = arr[i/2][0];
                inOutTime[i][1] = 1;
            } else {
                inOutTime[i][0] = arr[(i -1)/2][1];
                inOutTime[i][1] = 0;
            }
        }

        sortedArr(inOutTime);

//        for(int[] row: inOutTime){
//            System.out.println(row[0] + " "+ row[1] );
//        }

        for(int i=0; i< inOutTime.length-1; i++){
//
            if(inOutTime[i][1]==1){
                tmpSum++;
            }
            if(inOutTime[i][1]==0){
                tmpSum--;
            }
            if (tmpSum > globalSum){
                globalSum = tmpSum;
            }
        }

        System.out.printf("Ο μέγιστος αριθμός παρκαρισμένων αμαξιών είναι: %d", globalSum);

    }

    public static void sortedArr(int[][] arr){
        Arrays.sort(arr, (a1, a2) -> (a1[0] - a2[0]));
    }

}

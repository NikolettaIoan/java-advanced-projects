package projects.project01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Αναπτύξτε ένα πρόγραμμα σε Java που να διαβάζει από ένα αρχείο ακέραιους
 * αριθμούς μέχρι να βρει την τιμή -1 (το αρχείο πρέπει να περιέχει περισσότερους από
 * 6 αριθμούς και το πολύ 49 αριθμούς) με τιμές από 1 έως 49. Τους αριθμούς αυτούς
 * τους εισάγει σε ένα πίνακα, τον οποίο ταξινομεί (π.χ. με την Arrays.sort()). Στη
 * συνέχεια, το πρόγραμμα παράγει όλες τις δυνατές εξάδες (συνδυασμούς 6 αριθμών).
 * Ταυτόχρονα και αμέσως μετά την παραγωγή κάθε εξάδας ‘φιλτράρει’ κάθε εξάδα
 * ώστε να πληροί τα παρακάτω κριτήρια: 1) Να περιέχει το πολύ 4 άρτιους, 2) να
 * περιέχει το πολύ 4 περιττούς, 3) να περιέχει το πολύ 2 συνεχόμενους, 4) να περιέχει
 * το πολύ 3 ίδιους λήγοντες, 5) να περιέχει το πολύ 3 αριθμούς στην ίδια δεκάδα.
 * Τέλος, εκτυπώνει τις τελικές εξάδες σε ένα αρχείο με όνομα της επιλογής σας και
 * κατάληξη.txt.
 */
public class Project01 {
    public static void main(String[] args) throws FileNotFoundException {
        int[] randomNumbers;
        int n =6;
        String url = "randomOutput.txt";
        ArrayList<Integer> numbers = new ArrayList<>();
        PrintStream ps = new PrintStream("result.txt");


        //creating a random number list output
        randomNumbers = generateList(10, 49);
        try{
            outputRandomList(randomNumbers, url);
        } catch (FileNotFoundException | NullPointerException e){
           String message = String.format("%s", e.getMessage());
            System.out.printf("Please check your %s", message.equals("null") ? "list (null)" : String.format("url %s", url ));
        }

        try(Scanner sc= new Scanner(new File(url))){
            while (sc.hasNextInt()){
                numbers.add(sc.nextInt());
            }
        }catch (FileNotFoundException e){
            System.out.println("No file");
        }

       if(numbers.size() >0) {
            Collections.sort(numbers);  //mutates numbers
        }

       if(numbers.size() >0){
           for (int i = 0; i <= numbers.size() - n; i++){
               for (int j = i+1; j <= numbers.size() - n +1; j++){
                   for (int k = j+1; k <= numbers.size() -n +2; k++){
                       for (int l = k +1; l<= numbers.size() -n +3; l++){
                           for (int m = l +1; m <= numbers.size() -n +4; m++){
                               for (int p = m +1;  p < numbers.size(); p++){
                                   int[] temp = {numbers.get(i), numbers.get(j), numbers.get(k), numbers.get(l), numbers.get(m), numbers.get(p)};
                                   if (!isEven(temp) && !isOdd(temp) && !isContiguous(temp) && !isSameEnding(temp) && !isSameTen(temp)){
                                       for (int item : temp){
                                           ps.print(item + " ");

                                           }
                                           ps.println();
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }


    }

    /**
     * Generates a random list of numbers
     * @param listLength the length of the list
     * @param maxNum the maximum number
     * @return the random list
     */
    public static int[] generateList(int listLength, int maxNum){
        int[] randomNumbers = new int[listLength];

        for (int i =0; i<randomNumbers.length ; i++){
            int num = (int) ((Math.random()*maxNum) +1 );
            randomNumbers[i] = (int) ((Math.random()*maxNum) +1 );
        }
        return randomNumbers;
    }

    /**
     * export the generated list to a file
     * @param arr the list we want to store
     * @param url the given url
     * @throws FileNotFoundException to be handled my main
     */
    public static void outputRandomList(int[] arr, String url) throws FileNotFoundException {
        PrintStream randomOutput = new PrintStream(new FileOutputStream(url));

        for (int item:arr){
            try{
                randomOutput.print(item + " ");
            } catch (Exception e){
//                e.printStackTrace();
                throw e;
            }
        }
    }

    public static boolean isEven(int[] arr){
        int count=0;
        for (int item : arr){
            if (item % 2 == 0){
                count++;
            }
        }
        return count >4;
    }

    public static boolean isOdd(int[] arr){
        int count=0;
        for (int item : arr){
            if (item % 2 != 0){
                count++;
            }
        }
        return count >4;
    }

    public static boolean isContiguous(int[] arr){
        boolean isCont= false;
        for (int i = 0; i < arr.length-1 ; i++){
            if (arr[i] == arr[i+1]) {
               isCont =true;
            }
        }
        return isCont;
    }

    public static boolean isSameEnding(int[] arr){
        boolean isSame = false;

        for (int i = 0; i < arr.length-1 ; i++){
            int count = 0;
            for (int j = 0; j< arr.length ; j++){
                if ((arr[i]%10) == (arr[j]%10)) {
                    count++;
                }
            }
            if (count> 3){
                isSame =true;
                return isSame;
            }
        }
        return isSame;
    }

    public static boolean isSameTen(int[] arr){
        boolean isSame = false;

        for (int i = 0; i < arr.length-1 ; i++){
            int count = 0;
            for (int j = 0; j< arr.length ; j++){
                if ((arr[i]/10) == (arr[j]/10)) {
                    count++;
                }
            }
            if (count> 3){
                isSame =true;
                return isSame;
            }
        }
        return isSame;
    }
}


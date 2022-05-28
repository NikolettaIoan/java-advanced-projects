package projects.project10;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Έστω ένα θέατρο που έχει θέσεις όπου η κάθε θέση περιγράφεται με ένα χαρακτήρα
 * που είναι η στήλη και ένα αριθμό που είναι η σειρά. Για παράδειγμα η θέση C2
 * βρίσκεται στην 2η σειρά και 3η στήλη.
 * Αναπτύξτε ένα πρόγραμμα διαχείρισης θεάτρου με 30 σειρές και 12 στήλες. Πιο
 * συγκεκριμένα γράψτε μία μέθοδο void book(char column, int row) που να κάνει book
 * μία θέση αν δεν είναι ήδη booked και μία μέθοδο void cancel(char column, int row)
 * που να ακυρώνει την κράτηση μία θέσης αν είναι ήδη booked.
 *
 * (Για να πάρουμε τη σειρά αφαιρούμε 65 από το ASCII value.)
 */
public class Project10 {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        boolean[][] chartBooked= new boolean[30][12];
        int option = -1;

        for (boolean[] row: chartBooked){
            Arrays.fill(row, false);
        }

       do{
           showMenu();
           try {
               option = in.nextInt();
               System.out.println();
           }catch (InputMismatchException e){
               System.out.println("The choice should be a number (between 1-3)");
               in.nextLine();
           }
               switch (option){
                   case 1:
                       System.out.println("Book a seat!");
                       book(chartBooked);
                       System.out.println();
                   break;
                   case 2:
                       System.out.println("Cancel your reservation");
                       cancel(chartBooked);
                       System.out.println();
                       break;
                   case 3:
                       System.out.println("EXIT");
                       break;
                   case 4:
                       System.out.println("Please choose between 1-3");
                       break;
                   default:
                       System.out.println("The choice must be between 1-3");
                       break;
               }
           }while (option != 3);

        System.out.println("Thank You!");

    }
    public static void showMenu(){
        System.out.println("Choose an option");
        System.out.println("1. Book a seat");
        System.out.println("2. Canceled a booked seat");
        System.out.println("3. EXIT");
        System.out.print("Option: ");
    }
    public static int[] handleUsersChoice(){
        int userRow= -1;
        int userColumn = -1;
        int[] position = {-1, -1};
        char seatChar;

            try{
                System.out.println("Choose a row from 1-30");
                userRow = in.nextInt();
                if(userRow <= 0 || userRow>30){
                    System.out.println("Invalid Choice");
                }
                if(userRow > 0 && userRow <=30){
                    position[0] = userRow-1;
                }
                System.out.println("Choose a seat from A - L");
                seatChar = in.next().charAt(0);
                seatChar = Character.toUpperCase(seatChar);
                userColumn = seatChar - 65;
                if(userColumn <0 || userColumn > 11){
                    System.out.println("Invalid choice: " + seatChar);
                }
                if(userColumn >= 0 && userColumn <12){
                    position[1] = userColumn;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid Choice");
                in.nextLine();
            }
            return position;
    }

    public static void book(boolean[][] chartBooked){
        System.out.println("Choose a row and a seat");
        int[] position = handleUsersChoice();

            if(position[0] != -1 && position[1] != -1){
                if(!chartBooked[position[0]][position[1]]){
                    chartBooked[position[0]][position[1]] = true;
                    System.out.printf("Seat booked! You chose %c%s \n", (char)(position[1]+65),position[0]+1);
                }else{
                    System.out.println("Seat already taken!");
                }
            }
    }

    public static void cancel(boolean[][] chartBooked){
        System.out.println("Choose the row and column or the seat to delete!");
        int[] position = handleUsersChoice();

        if(position[0] != -1 && position[1] != -1){
            if(chartBooked[position[0]][position[1]]){
                chartBooked[position[0]][position[1]] = false;
                System.out.printf("Seat %c%s canceled \n", (char)(position[1]+65),position[0]+1);
            }else{
                System.out.println("Seat is not booked please check again!");
            }
        }
    }
}

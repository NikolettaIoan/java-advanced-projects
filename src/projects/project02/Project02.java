package projects.project02;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Δημιουργήστε μία εφαρμογή επαφών για ένα κινητό τηλέφωνο, η οποία μπορεί να
 * περιέχει μέχρι 500 επαφές. Κάθε επαφή έχει Επώνυμο, Όνομα και Τηλέφωνο.
 * Για να αποθηκεύεται τις επαφές χρησιμοποιήστε ένα δυσδιάστατο πίνακα 500x3
 * όπου στην 1η θέση κάθε επαφής θα αποθηκεύεται το Επώνυμο, στη 2η θέση το Όνομα
 * και στην 3η θέση το τηλέφωνο, όλα ως String.
 * Υλοποιήστε τις βασικές CRUD πράξεις: Αναζήτηση Επαφής με βάση το τηλέφωνο,
 * Εισαγωγή Επαφής (αν δεν υπάρχει ήδη), Ενημέρωση Επαφής (εάν υπάρχει),
 * Διαγραφή Επαφής (εάν υπάρχει).
 * Η main() θα πρέπει να εμφανίζει ένα μενού στον χρήστη, οποίος θα επιλέγει την
 * κατάλληλη πράξη ή Έξοδο από την εφαρμογή και με την κατάλληλη καθοδήγηση της
 * εφαρμογής θα επιτελεί την επιλεγμένη πράξη.
 */
public class Project02 {
    static int top = -1;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] catalog = new String[500][3];
        boolean appActive = true;
        int choice = 0;

        do{
            showMenu();
            try {
                choice = in.nextInt();
                switch (choice){
                  case 1:
                      if(top != -1){
                          System.out.println("Insert a phone number");
                          String phoneNumber = in.next();
                          readContact(catalog, phoneNumber);
                          System.out.println();
                      }else {
                          System.out.println("The catalog is empty!\n");
                      }
                      break;
                    case 2:
                        createContact(catalog);
                        break;
                    case 3:
                        System.out.println("Please insert the phone number for the contact to update");
                        String phone = in.next().trim();
                        int position = readContact(catalog, phone);
                        if(position>=0){
                            updateContact(catalog, position);
                        } else {
                            System.out.println("Phone number invalid");
                        }
                        break;
                    case 4:
                        System.out.print("Insert the phone number for the contact to delete:");
                        String phoneToDelete = in.next().trim();
                        int positionToDelete = readContact(catalog, phoneToDelete);
                        if(positionToDelete >= 0){
                            deleteContact(catalog,positionToDelete);
                        }else {
                            System.out.println("Phone number invalid");
                        }
                        break;
                    case 5:
                        System.out.println("You choose EXIT. Goodbye!");
                        break;
                    default:
                        System.out.println("Please choose between 1-5");
                        break;

              }
            }catch (InputMismatchException e){
                System.out.println("Please insert a number between 1-5");
                in.nextLine();
            }
        }while(choice !=5);
    }

    public static void createContact(String[][] arr) {
        boolean registered = false;
        System.out.println("Insert the new contact!");
        System.out.print("Firstname: ");
        String firstname = in.next().trim();
        System.out.print("Lastname: ");
        String lastname = in.next().trim();
        System.out.print("Phone: ");
        String phone = in.next().trim();

        for (int i =0; i <= top ; i++) {
            if (arr[i][2].equals(phone)) {
                System.out.printf("A contact already exists! %s %s %s\n", arr[i][0], arr[i][1], arr[i][2]);
                registered=true;
                break;
            }
        }

        if(!registered){
            top++;
            arr[top][0] = lastname;
            arr[top][1] = firstname;
            arr[top][2] = phone;
            System.out.printf("New contact %s %s %s created!\n", arr[top][0], arr[top][1], arr[top][2]);
        }
    }

    public static int readContact(String[][] arr, String phoneNumber){
        int position=-1;
        if(top !=-1){
            for (int i =0; i <= top ; i++){
                if (arr[i][2].equals(phoneNumber)){
                        position = i;
                }
            }
        }
        System.out.printf("%s", position>=0? String.format("%s %s %s", arr[position][0], arr[position][1], arr[position][2]):"The contact does not exist");
        System.out.println();
        return position;
    }

    public static void updateContact(String[][] arr, int index){
        System.out.print("Update Last Name: ");
        arr[index][0]= in.next().trim();

        System.out.print("Update First Name: ");
        arr[index][1]= in.next().trim();

        System.out.print("Update Phone Number: ");
        arr[index][2]= in.next().trim();
        System.out.printf("Contact updated! %s %s %s\n", arr[index][0], arr[index][1], arr[index][2]);
    }

    public static void deleteContact(String[][] arr, int index){
        for(int i = index; i <=top-1 ; i++){
            arr[i][0]= arr[i+1][0];
            arr[i][1]= arr[i+1][1];
            arr[i][2]= arr[i+1][2];
        }
        arr[top][0]=null;
        arr[top][1]=null;
        arr[top][2]=null;
        top--;
        System.out.println("Contact Deleted!");
    }

    public static void showMenu(){
        System.out.println("Choose an option");
        System.out.println("1. Find a contact");
        System.out.println("2. Register a new Contact");
        System.out.println("3. Update an existed contact");
        System.out.println("4. Delete an existed contact");
        System.out.println("5. EXIT");
        System.out.print("Option: ");
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import models.Contact;
import models.ContactManager;

public class Main {

    static ContactManager contactManager = new ContactManager();
    public static void main(String[] args) {
        try {
            loadContacts("contacts.txt");
            System.out.println("\n\nCONTACTS LOADED\n\n");
            System.out.println(contactManager);
            manageContacts();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("\nProcess complete\n");
        }
        
    }

    public static void loadContacts(String fileName) throws FileNotFoundException {
        
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            try {
                contactManager.addContact(new Contact(scan.next(), scan.next(), scan.next()));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }

        scan.close();
    }

    public static void manageContacts() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\nWould you like to \n\ta) add another contact\n\tb) remove a contact \n\tc) exit");
            String input = scan.nextLine();
            if (input.equals("a")) {
                System.out.print("Name: ");
                String name = scan.nextLine();
                System.out.print("Phone number: ");
                String phoneNumber = scan.nextLine();
                System.out.println("Birth date: ");
                String birthDate = scan.nextLine();
                if (name == null || name.isBlank() ||
                    phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() < 5
                    //birthDate == null || birthDate.isBlank()
                ) {
                    System.out.println("\nThe input you provided is not valid. Registration failed.");
                    continue;
                }
                try {
                    contactManager.addContact(new Contact(name, phoneNumber, birthDate));
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println("\n\nUPDATED CONTACTS\n\n");
                }
            }
            if (input.equals("b")) {
                System.out.println("\nWho would you like to remove?");
                String name = scan.nextLine();
                contactManager.removeContact(name);
                System.out.println("\n\nUPDATED CONTACTS");
            }
            if (input.equals("c")) {
                break;
            }
        }
        scan.close();
    }
}

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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
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
}

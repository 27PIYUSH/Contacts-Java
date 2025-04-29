package Controller;

import Service.ContactService;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactController {
    private static final String EMAIL_PATTERN = 
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private ContactService service;
    private Scanner scanner;

    public ContactController() {
        service = new ContactService();
        scanner = new Scanner(System.in);
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d+") && phone.length() >= 10;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        
        String[] commonTLDs = {".com", ".org", ".net", ".edu", ".gov"};
        boolean hasValidTLD = false;
        for (String tld : commonTLDs) {
            if (email.toLowerCase().endsWith(tld)) {
                hasValidTLD = true;
                break;
            }
        }
        
        return hasValidTLD && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public void start() {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                processChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 1 and 6!");
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Contacts ===");
        System.out.println("1. Add Contact");
        System.out.println("2. Delete Contact");
        System.out.println("3. Update Contact");
        System.out.println("4. Search Contacts");
        System.out.println("5. Display All Contacts");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1: addContact(); break;
            case 2: deleteContact(); break;
            case 3: updateContact(); break;
            case 4: searchContacts(); break;
            case 5: displayAllContacts(); break;
            case 6: exit(); break;
            default: System.out.println("Please enter a number between 1 and 6!");
        }
    }

    private void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        String phone;
        do {
            System.out.print("Enter phone number (minimum 10 digits): ");
            phone = scanner.nextLine();
            if (!isValidPhoneNumber(phone)) {
                System.out.println("Invalid phone number! Please enter numbers only.");
            }
        } while (!isValidPhoneNumber(phone));
        
        String email;
        do {
            System.out.print("Enter email (example@domain.com): ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format! Please try again.");
            }
        } while (!isValidEmail(email));
        
        service.addContact(name, phone, email);
    }

    private void deleteContact() {
        System.out.print("Enter name to delete: ");
        service.deleteContact(scanner.nextLine());
    }

    private void updateContact() {
        System.out.print("Enter name to update: ");
        String name = scanner.nextLine();
        
        String phone;
        do {
            System.out.print("Enter new phone number (minimum 10 digits): ");
            phone = scanner.nextLine();
            if (!isValidPhoneNumber(phone)) {
                System.out.println("Invalid phone number! Please enter numbers only.");
            }
        } while (!isValidPhoneNumber(phone));
        
        String email;
        do {
            System.out.print("Enter new email (example@domain.com): ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format! Please try again.");
            }
        } while (!isValidEmail(email));
        
        service.updateContact(name, phone, email);
    }

    private void searchContacts() {
        System.out.print("Enter search term: ");
        service.searchContacts(scanner.nextLine());
    }

    private void displayAllContacts() {
        service.displayAllContacts();
    }

    private void exit() {
        System.out.println("Get Lost!");
        scanner.close();
        System.exit(0);
    }
}

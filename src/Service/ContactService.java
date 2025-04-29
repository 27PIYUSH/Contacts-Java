package Service;

import Entity.Contact;
import Repo.ContactRepository;
import java.util.*;

public class ContactService {
    private ContactRepository repository;

    public ContactService() {
        repository = new ContactRepository();
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact contact = new Contact(name, phoneNumber, email);
        repository.save(contact);
        System.out.println("Contact added successfully!");
    }

    public void deleteContact(String name) {
        if (repository.delete(name)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    public void updateContact(String name, String newPhone, String newEmail) {
        Contact contact = repository.findByName(name);
        if (contact != null) {
            contact.setPhoneNumber(newPhone);
            contact.setEmail(newEmail);
            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    public void searchContacts(String searchTerm) {
        ArrayList<Contact> results = repository.search(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No contacts found with that search term!");
        } else {
            results.forEach(System.out::println);
        }
    }

    public void displayAllContacts() {
        ArrayList<Contact> contacts = repository.findAll();
        if (contacts.isEmpty()) {
            System.out.println("No contacts in the list!");
            return;
        }

        // Basic bubble sort implementation
        for (int i = 0; i < contacts.size() - 1; i++) {
            for (int j = 0; j < contacts.size() - i - 1; j++) {
                if (contacts.get(j).getName().compareToIgnoreCase(contacts.get(j + 1).getName()) > 0) {
                    // Swap contacts
                    Contact temp = contacts.get(j);
                    contacts.set(j, contacts.get(j + 1));
                    contacts.set(j + 1, temp);
                }
            }
        }

        // Basic for loop for printing
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
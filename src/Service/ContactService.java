package Service;

import Entity.Contact;
import Repo.ContactRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        Collections.sort(contacts, Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        contacts.forEach(System.out::println);
    }
}

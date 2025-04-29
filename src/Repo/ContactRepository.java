package Repo;

import Entity.Contact;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactRepository {
    private ArrayList<Contact> contacts;

    public ContactRepository() {
        contacts = new ArrayList<>();
    }

    public void save(Contact contact) {
        contacts.add(contact);
    }

    public boolean delete(String name) {
        return contacts.removeIf(contact -> 
            contact.getName().equalsIgnoreCase(name));
    }

    public Contact findByName(String name) {
        return contacts.stream()
            .filter(contact -> contact.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public ArrayList<Contact> findAll() {
        return new ArrayList<>(contacts);
    }

    public ArrayList<Contact> search(String searchTerm) {
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().startsWith(searchTerm.toLowerCase())) {
                results.add(contact);
            }
        }
        return results;
    }
}

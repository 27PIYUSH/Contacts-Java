package Repo;

import Entity.Contact;
import java.util.*;

public class ContactRepository {
    private ArrayList<Contact> contacts;

    public ContactRepository() {
        contacts = new ArrayList<>();
    }

    public void save(Contact contact) {
        contacts.add(contact);
    }

    public boolean delete(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    public Contact findByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
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
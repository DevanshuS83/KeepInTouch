package com.keepintouch.kit.services.impl;

import com.keepintouch.kit.helpers.ResourceNotFoundException;
import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.repos.ContactRepo;
import com.keepintouch.kit.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo repo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return repo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // TODO: Implement the update method
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return repo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact with id "+id+" not found"));
    }

    @Override
    public void delete(String id) {
        Contact contact = getById(id);
        repo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO: Implement
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Contact> getByUser(User user) {
        return repo.findByUser(user);
    }
}

package com.keepintouch.kit.services.impl;

import com.keepintouch.kit.helpers.ResourceNotFoundException;
import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.repos.ContactRepo;
import com.keepintouch.kit.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        var oldContact = repo.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        oldContact.setName(contact.getName());
        oldContact.setEmail(contact.getEmail());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        oldContact.setAddress(contact.getAddress());
        oldContact.setDescription(contact.getDescription());
        oldContact.setWebsiteLink(contact.getWebsiteLink());
        oldContact.setLinkedInLink(contact.getLinkedInLink());
        oldContact.setPicture(contact.getPicture());
        oldContact.setFavorite(contact.isFavorite());
        oldContact.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
        return repo.save(oldContact);
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
    public Page<Contact> searchByName(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(pageNo, pageSize, sort);
        return repo.findByUserAndNameContaining(user, keyword, pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(pageNo, pageSize, sort);
        return repo.findByUserAndEmailContaining(user, keyword, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user) {
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(pageNo, pageSize, sort);
        return repo.findByUserAndPhoneNumberContaining(user, keyword, pageable);
    }

    @Override
    public Page<Contact> getByUserId(String userId, int pageNo, int pageSize, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(pageNo, pageSize, sort);
        return repo.findByUserId(userId, pageable);
    }

    @Override
    public Page<Contact> getByUser(User user, int pageNo, int pageSize, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(pageNo, pageSize, sort);
        return repo.findByUser(user, pageable);
    }


}

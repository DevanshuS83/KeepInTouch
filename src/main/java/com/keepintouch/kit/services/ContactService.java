package com.keepintouch.kit.services;

import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Contact getById(String id);
    void delete(String id);
    Page<Contact> searchByName(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user);
    Page<Contact> searchByEmail(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user);
    Page<Contact> searchByPhoneNumber(String keyword, int pageNo, int pageSize, String sortBy, String direction, User user);
    Page<Contact> getByUserId(String userId, int pageNo, int pageSize, String sortBy, String direction);
    Page<Contact> getByUser(User user, int pageNo, int pageSize, String sortBy, String direction);
}

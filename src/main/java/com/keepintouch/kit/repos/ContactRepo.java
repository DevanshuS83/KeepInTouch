package com.keepintouch.kit.repos;

import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, String> {
    @Query("select c from Contact c where c.user.userId = :userId")
    Page<Contact> findByUserId(@Param("userId") String userId, PageRequest pageable);

    Page<Contact> findByUser(User user, PageRequest pageable);

    Page<Contact> findByUserAndNameContaining(User user, String keyword, Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user, String keyword, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String keyword, Pageable pageable);

}

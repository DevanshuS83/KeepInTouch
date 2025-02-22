package com.keepintouch.kit.repos;

import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, String> {
    // find contacts by userId
    List<Contact> findByUser(User user);

    @Query("select c from Contact c where c.user.userId = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}

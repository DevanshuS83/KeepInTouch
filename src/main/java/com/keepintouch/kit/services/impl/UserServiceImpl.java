package com.keepintouch.kit.services.impl;

import com.keepintouch.kit.helpers.ResourceNotFoundException;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.repos.UserRepo;
import com.keepintouch.kit.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<User> saveUser(User user) {
        // generating user id dynamically
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // TODO: Encode password before saving into the database
        return Optional.of(repo.save(user));
    }

    @Override
    public Optional<User> getById(String id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User dbuser = repo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        // update dbuser using the user object
        dbuser.setName(user.getName());
        dbuser.setEmail(user.getEmail());
        dbuser.setPassword(user.getPassword());
        dbuser.setAbout(user.getAbout());
        dbuser.setPhoneNumber(user.getPhoneNumber());
        dbuser.setProfilePic(user.getProfilePic());
        dbuser.setEnabled(user.isEnabled());
        dbuser.setEmailVerified(user.isEmailVerified());
        dbuser.setPhoneVerified(user.isPhoneVerified());
        dbuser.setProvider(user.getProvider());
        dbuser.setProviderUserId(user.getProviderUserId());

        // saving the user in the database
        return Optional.of(repo.save(dbuser));
    }

    @Override
    public void deleteUser(String id) {
        User user2 = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExists(String id) {
        User user2 = repo.findById(id).orElse(null);
        return user2 != null;
    }

    @Override
    public boolean isUserExistsWithEmail(String email) {
        User user = (User) repo.findByEmail(email).orElse(null);
        return user != null;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(repo.findAll());
    }
}

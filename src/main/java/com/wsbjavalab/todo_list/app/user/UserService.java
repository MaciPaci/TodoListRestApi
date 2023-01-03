package com.wsbjavalab.todo_list.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(long userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return getUser(user.getId());
    }

    public User updateUser(long userID, UpdateUser updateBody) {
        User userToUpdate = getUser(userID);
        userRepository.save(userToUpdate.updateFields(updateBody));
        return getUser(userID);
    }

    public void deleteUser(long userID) {
        userRepository.deleteById(userID);
    }

    public boolean isUserIdValid(long id) {
        return userRepository.findById(id).isPresent();
    }
}

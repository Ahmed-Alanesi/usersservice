package com.example.usersservice.service.impl;


import com.example.usersservice.model.ItemExistException;
import com.example.usersservice.model.ItemNotExistException;
import com.example.usersservice.model.User;
import com.example.usersservice.model.UserResource;
import com.example.usersservice.repository.UserRepository;
import com.example.usersservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResource registerNewUser(User user) {
        checkEmailNotExist(user.getEmail());
        return userRepository.save(user).toUserResource();
    }


    @Override
    public UserResource findById(Long userId) throws ItemNotExistException {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new ItemNotExistException(String.format("User with id %s not exist", userId));
        }

        return foundUser.get().toUserResource();
    }

    @Override
    public List<UserResource> getAll() {
        return userRepository.findAll()
                .stream()
                .map(User::toUserResource)
                .collect(Collectors.toList());
    }

    @Override
    public UserResource updateUser(User user, Long id) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ItemNotExistException(
                        String.format("User with id %s not exist", id)));

        // TODO: 2021-08-17 14:47:00 check if email is unique among all users

        foundUser.setEmail(user.getEmail());
        foundUser.setTitle(user.getTitle());
        foundUser.setFirstname(user.getFirstname());
        foundUser.setLastname(user.getLastname());
        foundUser.setPhone(user.getPhone());

        return userRepository.save(foundUser).toUserResource();
    }

    /**
     * When adding, submitted username should be unique among all users.
     *
     * @param email Username to be checked.
     * @throws ItemExistException when username exist for other user.
     */
    private void checkEmailNotExist(String email)
            throws ItemExistException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) throw new ItemExistException(
                String.format("User with email %s already exist.", email));
    }

}

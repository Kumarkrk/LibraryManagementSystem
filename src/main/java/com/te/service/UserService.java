
package com.te.service;

import java.util.Set;

import com.te.dao.UserDao;
import com.te.model.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class UserService {

    private final UserDao userDao = new UserDao();

    private final Validator validator =
        Validation.buildDefaultValidatorFactory().getValidator();

    public User registerUser(User user) {
        Set<ConstraintViolation<User>> errors =
            validator.validate(user);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(
                errors.iterator().next().getMessage());
        }

        if (userDao.countByUsername(user.getUsername()) > 0) {
            throw new IllegalArgumentException(
                "Username already exists");
        }

        return userDao.save(user);
    }

    public User getUserByUsername(String username) {
        if (username == null || username.isBlank()) {
            return null;
        }

        return userDao.findByUsername(username);
    }
}

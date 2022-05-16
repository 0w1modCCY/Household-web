package cz.cvut.fit.household.service;

import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.repository.user.jpa.UserRepository;
import cz.cvut.fit.household.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersBySearchTerm(String searchTerm) {
        return userRepository.searchByUsername(searchTerm);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public Boolean exists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteById(username);
    }

}

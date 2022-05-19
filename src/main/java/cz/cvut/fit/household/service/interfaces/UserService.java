package cz.cvut.fit.household.service.interfaces;

import cz.cvut.fit.household.datamodel.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface which provides CRUD operations for users {@link User},
 * with extended searches
 * @see User
 */
public interface UserService {
    /**
     * @param user which is going to be added in database
     * @return freshly saved user
     */
    User createOrUpdateUser(User user);

    /**
     * @return all existed users in database
     */
    List<User> findAllUsers();

    /**
     * Searches for the matches in users usernames with given string
     *
     * @param searchTerm is username which probably similar to some users usernames
     * @return list of users, whose usernames match with given username
     */
    List<User> findUsersBySearchTerm(String searchTerm);

    /**
     * Searches for the user, using username
     *
     * @param username of needed user
     * @return user, with given username, otherwise null
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Checks if user with given username exists
     *
     * @param username of needed username
     * @return true if user founded, otherwise false
     */
    Boolean exists(String username);

    /**
     * Searching and deleting user, using username
     *
     * @param username of needed user
     */
    void deleteUserByUsername(String username);
}

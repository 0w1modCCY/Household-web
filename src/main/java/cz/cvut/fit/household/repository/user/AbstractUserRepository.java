package cz.cvut.fit.household.repository.user;


import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.repository.AbstractRepository;

import java.util.List;

public interface AbstractUserRepository extends AbstractRepository<String, User> {

    List<User> findUsersByUsernameStartingWith(String searchTerm);
}

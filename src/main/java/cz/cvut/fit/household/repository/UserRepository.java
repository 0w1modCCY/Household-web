package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUsersByUsernameStartingWith(String searchTerm);
}

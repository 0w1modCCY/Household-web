package cz.cvut.fit.household.repository.user;


import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbstractUserRepository extends AbstractRepository<String, User> {

    @Query(value = "select m from User m where m.username like %?1%")
    List<User> searchByUsername(String searchTerm);
}

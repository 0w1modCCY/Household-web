package cz.cvut.fit.household.repository.user.jpa;

import cz.cvut.fit.household.datamodel.entity.User;
import cz.cvut.fit.household.repository.user.AbstractUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends AbstractUserRepository, JpaRepository<User, String>  {

}

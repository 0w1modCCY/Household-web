package cz.cvut.fit.household.repository;

import cz.cvut.fit.household.datamodel.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy,Long> {
}

package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Authority of a user inside scope of the specific household.
 *
 * @see User
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private User user;
}

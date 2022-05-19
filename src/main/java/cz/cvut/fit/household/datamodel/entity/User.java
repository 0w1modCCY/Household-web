package cz.cvut.fit.household.datamodel.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * User class is responsible for storing general information about users of the application. User entity is created
 * when new user is registered in the application. For more info read about authorities {@link Authority} and membership
 * class {@link Membership}
 *
 * @see HouseHold
 * @see Item
 * @see Membership
 * @see Authority
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @NotBlank(message = "Username is empty")
    private String username;

    @NotBlank(message = "Password is empty")
    private String password;

    @NotBlank(message = "First name is empty")
    private String firstName;

    @NotBlank(message = "Last name is empty")
    private String lastName;

    @Email(message = "Invalid format of email")
    @NotBlank(message = "Email is empty")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private List<Membership> memberships = new ArrayList<>();

    public void addMembership(Membership membership) {
        memberships.add(membership);
        membership.setUser(this);
    }
}

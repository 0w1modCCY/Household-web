package cz.cvut.fit.household.datamodel.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String username;

    @NotNull
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
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

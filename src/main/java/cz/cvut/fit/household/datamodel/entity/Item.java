package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<Copy> copies;
//
//    @ManyToOne
//    private Category category;

    @NotBlank
    private String title;

    private String description;

    public void addCopy(Copy copy) {copies.add(copy);}

    public void removeCopy(Copy copy) {copies.remove(copy);}
}

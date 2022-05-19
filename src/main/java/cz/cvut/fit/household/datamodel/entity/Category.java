package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
/**
 * Categories of the items, which provided to improve managing, and sorting
 * items.
 *
 * @see Item
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToMany(mappedBy = "category")
//    private List<Item> items;
//
//    @ManyToOne
//    @JoinColumn(name = "main_id")
//    private Category mainCategory;
//
//    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.REMOVE)
//    private List<Category> subCategories = new ArrayList<>();

    @NotBlank
    private String title;

    private String description;
}

package cz.cvut.fit.household.datamodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Tracker which can make a records of the specific resource type.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

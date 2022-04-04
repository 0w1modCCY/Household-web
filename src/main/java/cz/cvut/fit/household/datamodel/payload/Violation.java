package cz.cvut.fit.household.datamodel.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Violation {
    private String fieldName;

    private String message;
}

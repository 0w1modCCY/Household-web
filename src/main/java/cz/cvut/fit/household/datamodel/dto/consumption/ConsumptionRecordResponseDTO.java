package cz.cvut.fit.household.datamodel.dto.consumption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ConsumptionRecordResponseDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double bill;
    private String resourceType;
}

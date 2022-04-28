package cz.cvut.fit.household.datamodel.dto.consumption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ConsumptionRecordPostDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double consumption;
    private String resourceType;

    public ConsumptionRecordPostDTO(LocalDate startDate, LocalDate endDate, Double consumption, String resourceType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.consumption = consumption;
        this.resourceType = resourceType;
    }
}

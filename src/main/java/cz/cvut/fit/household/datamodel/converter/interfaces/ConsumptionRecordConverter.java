package cz.cvut.fit.household.datamodel.converter.interfaces;

import cz.cvut.fit.household.datamodel.dto.consumption.ConsumptionRecordPostDTO;
import cz.cvut.fit.household.datamodel.dto.consumption.ConsumptionRecordResponseDTO;
import cz.cvut.fit.household.datamodel.entity.ConsumptionRecord;
import org.mapstruct.Mapper;

@Mapper
public interface ConsumptionRecordConverter {
    ConsumptionRecord toModelFromPostDto(ConsumptionRecordPostDTO consumptionRecordPostDTO);
    ConsumptionRecordResponseDTO fromModelToResponseDto (ConsumptionRecord consumptionRecord);
}

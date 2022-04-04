package cz.cvut.fit.household.datamodel.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse {

    private HttpStatus httpStatus;

    private LocalDateTime time;

    private final List<Violation> violations = new ArrayList<>();
}

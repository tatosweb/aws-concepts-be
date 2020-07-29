package co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollDto implements Serializable {

    //private Long id;
    private String name;
    private String lastName;
    private int age;
    private String preferredLanguage;
    private String workPlace;
    private String profession;
    private OffsetDateTime registrationDate;
}

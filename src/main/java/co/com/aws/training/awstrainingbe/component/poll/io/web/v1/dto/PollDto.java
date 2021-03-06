package co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollDto implements Serializable {

  private Long id;
  private String name;
  private String lastName;
  private int age;
  private String preferredLanguage;
  private String workPlace;
  private String profession;
  @JsonProperty(value = "timestamp", required = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private OffsetDateTime registrationDate;
}

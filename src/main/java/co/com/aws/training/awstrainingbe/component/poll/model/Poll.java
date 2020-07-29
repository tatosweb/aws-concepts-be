package co.com.aws.training.awstrainingbe.component.poll.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "programing_poll")
public class Poll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private String preferredLanguage;
    private String workPlace;
    private String profession;
    private OffsetDateTime registrationDate;
}

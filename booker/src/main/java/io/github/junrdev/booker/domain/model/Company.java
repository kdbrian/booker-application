package io.github.junrdev.booker.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company  {

    @Id private String id;

    private String name;

    private String location;

    @Builder.Default()
    private String dateJoined = LocalDate.now().toString();

    @Builder.Default()
    private Set<String> schedules = new HashSet<>();


    public void addSchedule(String scheduleID){
        if (schedules == null)
            schedules = new HashSet<>();
        schedules.add(scheduleID);
    }
}

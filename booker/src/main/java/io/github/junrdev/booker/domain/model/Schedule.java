package io.github.junrdev.booker.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Document
@Data
@Builder
public class Schedule {

    public static IDComparator IDComparator = new IDComparator();
    public static StartTimeComparator StartTimeComparator = new StartTimeComparator();
    public static EndTimeComparator EndTimeComparator = new EndTimeComparator();


    @Id
    private String id;

    //    @NotNull(message = "Object moved or missing.")
    @DBRef
    private Company company;

    private Long startTime;

    private Long endTime;


    public static class IDComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return o1.id.compareTo(o2.id);
        }
    }


    public static class StartTimeComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return (int) (o1.startTime - o2.startTime);
        }
    }

    public static class EndTimeComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return (int) (o1.endTime - o2.endTime);
        }
    }

}

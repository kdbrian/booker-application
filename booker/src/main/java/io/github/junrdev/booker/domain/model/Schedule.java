package io.github.junrdev.booker.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    public static RoutesCountComparator RoutesCountComparator = new RoutesCountComparator();


    @Id
    private String id;

    @DBRef
    private Company company;

    private Long startTime;

    private Long endTime;

    @DBRef
    @NotNull(message = "Routes cannot be null")
    private Set<Route> routes;

    public void addRoute(Route route) {
        routes.add(route);
    }


    public void addRoutes(List<Route> newRoutes) {
        routes.addAll(newRoutes);
    }


    public static class IDComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return o1.id.compareTo(o2.id);
        }
    }

    public static class RoutesCountComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return o1.routes.size() - o2.routes.size();
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

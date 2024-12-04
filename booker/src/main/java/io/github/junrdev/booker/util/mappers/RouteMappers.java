package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteMappers extends EntityToDtoMapper<Route, RouteDto> {
    @Override
    public Route fromDto(RouteDto dto) {
        return Route.builder()
                .from(dto.getFrom())
                .to(dto.getTo())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    @Override
    public RouteDto toDto(Route route) {
        return RouteDto.builder()
                .scheduleID(route.getSchedule().getId())
                .from(route.getFrom())
                .to(route.getTo())
                .createdAt(route.getCreatedAt())
                .build();
    }
}

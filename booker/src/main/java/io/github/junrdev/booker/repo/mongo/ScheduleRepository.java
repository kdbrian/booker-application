package io.github.junrdev.booker.repo.mongo;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.NonNullApi;
import com.mongodb.lang.Nullable;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.model.Schedule;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> findByCompany(Company company);

    void deleteById(@NonNull String scheduleId);


}

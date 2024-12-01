package io.github.junrdev.booker;

import io.github.junrdev.booker.domain.dto.ScheduleDto;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.util.mappers.ScheduleMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "io.github.junrdev.booker.repo.mongo")
public class BookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookerApplication.class, args);
	}


	@Bean
	public ScheduleMapper scheduleMapper(){
		return new ScheduleMapper();
	}


	@Bean
	public Validator validator(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
	}

}
